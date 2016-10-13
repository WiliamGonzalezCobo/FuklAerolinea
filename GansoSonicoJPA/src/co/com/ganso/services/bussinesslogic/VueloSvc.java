/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussinesslogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import co.com.ganso.entities.Aeropuerto;
import co.com.ganso.entities.Operacion;
import co.com.ganso.entities.Vuelo;
import co.com.ganso.services.bussiness.IVueloLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.IllegalOrphanException;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Stateless
public class VueloSvc implements IVueloLocalSvc {

	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;

    @Override
    public void create(Vuelo vuelo) throws PreexistingEntityException, Exception {
        if (vuelo.getTbOperacionList() == null) {
            vuelo.setTbOperacionList(new ArrayList<Operacion>());
        }
        try {
            em.getTransaction().begin();
            Aeropuerto TOrigen = vuelo.getTOrigen();
            if (TOrigen != null) {
                TOrigen = em.getReference(TOrigen.getClass(), TOrigen.getTCodigo());
                vuelo.setTOrigen(TOrigen);
            }
            Aeropuerto TDestino = vuelo.getTDestino();
            if (TDestino != null) {
                TDestino = em.getReference(TDestino.getClass(), TDestino.getTCodigo());
                vuelo.setTDestino(TDestino);
            }
            List<Operacion> attachedTbOperacionList = new ArrayList<Operacion>();
            for (Operacion tbOperacionListOperacionToAttach : vuelo.getTbOperacionList()) {
                tbOperacionListOperacionToAttach = em.getReference(tbOperacionListOperacionToAttach.getClass(), tbOperacionListOperacionToAttach.getNIdoperacion());
                attachedTbOperacionList.add(tbOperacionListOperacionToAttach);
            }
            vuelo.setTbOperacionList(attachedTbOperacionList);
            em.persist(vuelo);
            if (TOrigen != null) {
                TOrigen.getTbVueloList().add(vuelo);
                TOrigen = em.merge(TOrigen);
            }
            if (TDestino != null) {
                TDestino.getTbVueloList().add(vuelo);
                TDestino = em.merge(TDestino);
            }
            for (Operacion tbOperacionListOperacion : vuelo.getTbOperacionList()) {
                Vuelo oldNVueloOfTbOperacionListOperacion = tbOperacionListOperacion.getNVuelo();
                tbOperacionListOperacion.setNVuelo(vuelo);
                tbOperacionListOperacion = em.merge(tbOperacionListOperacion);
                if (oldNVueloOfTbOperacionListOperacion != null) {
                    oldNVueloOfTbOperacionListOperacion.getTbOperacionList().remove(tbOperacionListOperacion);
                    oldNVueloOfTbOperacionListOperacion = em.merge(oldNVueloOfTbOperacionListOperacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVuelo(vuelo.getNIdvuelo()) != null) {
                throw new PreexistingEntityException("Vuelo " + vuelo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Vuelo vuelo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            Vuelo persistentVuelo = em.find(Vuelo.class, vuelo.getNIdvuelo());
            Aeropuerto TOrigenOld = persistentVuelo.getTOrigen();
            Aeropuerto TOrigenNew = vuelo.getTOrigen();
            Aeropuerto TDestinoOld = persistentVuelo.getTDestino();
            Aeropuerto TDestinoNew = vuelo.getTDestino();
            List<Operacion> tbOperacionListOld = persistentVuelo.getTbOperacionList();
            List<Operacion> tbOperacionListNew = vuelo.getTbOperacionList();
            List<String> illegalOrphanMessages = null;
            for (Operacion tbOperacionListOldOperacion : tbOperacionListOld) {
                if (!tbOperacionListNew.contains(tbOperacionListOldOperacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Operacion " + tbOperacionListOldOperacion + " since its NVuelo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (TOrigenNew != null) {
                TOrigenNew = em.getReference(TOrigenNew.getClass(), TOrigenNew.getTCodigo());
                vuelo.setTOrigen(TOrigenNew);
            }
            if (TDestinoNew != null) {
                TDestinoNew = em.getReference(TDestinoNew.getClass(), TDestinoNew.getTCodigo());
                vuelo.setTDestino(TDestinoNew);
            }
            List<Operacion> attachedTbOperacionListNew = new ArrayList<Operacion>();
            for (Operacion tbOperacionListNewOperacionToAttach : tbOperacionListNew) {
                tbOperacionListNewOperacionToAttach = em.getReference(tbOperacionListNewOperacionToAttach.getClass(), tbOperacionListNewOperacionToAttach.getNIdoperacion());
                attachedTbOperacionListNew.add(tbOperacionListNewOperacionToAttach);
            }
            tbOperacionListNew = attachedTbOperacionListNew;
            vuelo.setTbOperacionList(tbOperacionListNew);
            vuelo = em.merge(vuelo);
            if (TOrigenOld != null && !TOrigenOld.equals(TOrigenNew)) {
                TOrigenOld.getTbVueloList().remove(vuelo);
                TOrigenOld = em.merge(TOrigenOld);
            }
            if (TOrigenNew != null && !TOrigenNew.equals(TOrigenOld)) {
                TOrigenNew.getTbVueloList().add(vuelo);
                TOrigenNew = em.merge(TOrigenNew);
            }
            if (TDestinoOld != null && !TDestinoOld.equals(TDestinoNew)) {
                TDestinoOld.getTbVueloList().remove(vuelo);
                TDestinoOld = em.merge(TDestinoOld);
            }
            if (TDestinoNew != null && !TDestinoNew.equals(TDestinoOld)) {
                TDestinoNew.getTbVueloList().add(vuelo);
                TDestinoNew = em.merge(TDestinoNew);
            }
            for (Operacion tbOperacionListNewOperacion : tbOperacionListNew) {
                if (!tbOperacionListOld.contains(tbOperacionListNewOperacion)) {
                    Vuelo oldNVueloOfTbOperacionListNewOperacion = tbOperacionListNewOperacion.getNVuelo();
                    tbOperacionListNewOperacion.setNVuelo(vuelo);
                    tbOperacionListNewOperacion = em.merge(tbOperacionListNewOperacion);
                    if (oldNVueloOfTbOperacionListNewOperacion != null && !oldNVueloOfTbOperacionListNewOperacion.equals(vuelo)) {
                        oldNVueloOfTbOperacionListNewOperacion.getTbOperacionList().remove(tbOperacionListNewOperacion);
                        oldNVueloOfTbOperacionListNewOperacion = em.merge(oldNVueloOfTbOperacionListNewOperacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = vuelo.getNIdvuelo();
                if (findVuelo(id) == null) {
                    throw new NonexistentEntityException("The vuelo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        try {
            em.getTransaction().begin();
            Vuelo vuelo;
            try {
                vuelo = em.getReference(Vuelo.class, id);
                vuelo.getNIdvuelo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vuelo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Operacion> tbOperacionListOrphanCheck = vuelo.getTbOperacionList();
            for (Operacion tbOperacionListOrphanCheckOperacion : tbOperacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vuelo (" + vuelo + ") cannot be destroyed since the Operacion " + tbOperacionListOrphanCheckOperacion + " in its tbOperacionList field has a non-nullable NVuelo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Aeropuerto TOrigen = vuelo.getTOrigen();
            if (TOrigen != null) {
                TOrigen.getTbVueloList().remove(vuelo);
                TOrigen = em.merge(TOrigen);
            }
            Aeropuerto TDestino = vuelo.getTDestino();
            if (TDestino != null) {
                TDestino.getTbVueloList().remove(vuelo);
                TDestino = em.merge(TDestino);
            }
            em.remove(vuelo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Vuelo> findVueloEntities() {
        return findVueloEntities(true, -1, -1);
    }
    
    @Override
    public List<Vuelo> findVueloEntities(int maxResults, int firstResult) {
        return findVueloEntities(false, maxResults, firstResult);
    }

    private List<Vuelo> findVueloEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vuelo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Vuelo findVuelo(BigDecimal id) {
        try {
            return em.find(Vuelo.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getVueloCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vuelo> rt = cq.from(Vuelo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
