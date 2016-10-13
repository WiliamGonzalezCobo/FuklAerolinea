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

import co.com.ganso.entities.ClaseVuelo;
import co.com.ganso.entities.FactorPrecio;
import co.com.ganso.entities.Operacion;
import co.com.ganso.services.bussiness.IClaseVueloLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Stateless
public class ClaseVueloSvc implements IClaseVueloLocalSvc {

	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;
    
    @Override
    public void create(ClaseVuelo claseVuelo) throws PreexistingEntityException, Exception {
        if (claseVuelo.getTbFactorprecioList() == null) {
            claseVuelo.setTbFactorprecioList(new ArrayList<FactorPrecio>());
        }
        if (claseVuelo.getTbOperacionList() == null) {
            claseVuelo.setTbOperacionList(new ArrayList<Operacion>());
        }
        try {
            em.getTransaction().begin();
            List<FactorPrecio> attachedTbFactorprecioList = new ArrayList<FactorPrecio>();
            for (FactorPrecio tbFactorprecioListFactorPrecioToAttach : claseVuelo.getTbFactorprecioList()) {
                tbFactorprecioListFactorPrecioToAttach = em.getReference(tbFactorprecioListFactorPrecioToAttach.getClass(), tbFactorprecioListFactorPrecioToAttach.getNIdfactor());
                attachedTbFactorprecioList.add(tbFactorprecioListFactorPrecioToAttach);
            }
            claseVuelo.setTbFactorprecioList(attachedTbFactorprecioList);
            List<Operacion> attachedTbOperacionList = new ArrayList<Operacion>();
            for (Operacion tbOperacionListOperacionToAttach : claseVuelo.getTbOperacionList()) {
                tbOperacionListOperacionToAttach = em.getReference(tbOperacionListOperacionToAttach.getClass(), tbOperacionListOperacionToAttach.getNIdoperacion());
                attachedTbOperacionList.add(tbOperacionListOperacionToAttach);
            }
            claseVuelo.setTbOperacionList(attachedTbOperacionList);
            em.persist(claseVuelo);
            for (FactorPrecio tbFactorprecioListFactorPrecio : claseVuelo.getTbFactorprecioList()) {
                ClaseVuelo oldNClaseOfTbFactorprecioListFactorPrecio = tbFactorprecioListFactorPrecio.getNClase();
                tbFactorprecioListFactorPrecio.setNClase(claseVuelo);
                tbFactorprecioListFactorPrecio = em.merge(tbFactorprecioListFactorPrecio);
                if (oldNClaseOfTbFactorprecioListFactorPrecio != null) {
                    oldNClaseOfTbFactorprecioListFactorPrecio.getTbFactorprecioList().remove(tbFactorprecioListFactorPrecio);
                    oldNClaseOfTbFactorprecioListFactorPrecio = em.merge(oldNClaseOfTbFactorprecioListFactorPrecio);
                }
            }
            for (Operacion tbOperacionListOperacion : claseVuelo.getTbOperacionList()) {
                ClaseVuelo oldNClaseOfTbOperacionListOperacion = tbOperacionListOperacion.getNClase();
                tbOperacionListOperacion.setNClase(claseVuelo);
                tbOperacionListOperacion = em.merge(tbOperacionListOperacion);
                if (oldNClaseOfTbOperacionListOperacion != null) {
                    oldNClaseOfTbOperacionListOperacion.getTbOperacionList().remove(tbOperacionListOperacion);
                    oldNClaseOfTbOperacionListOperacion = em.merge(oldNClaseOfTbOperacionListOperacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClaseVuelo(claseVuelo.getNIdclase()) != null) {
                throw new PreexistingEntityException("ClaseVuelo " + claseVuelo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public void edit(ClaseVuelo claseVuelo) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            ClaseVuelo persistentClaseVuelo = em.find(ClaseVuelo.class, claseVuelo.getNIdclase());
            List<FactorPrecio> tbFactorprecioListOld = persistentClaseVuelo.getTbFactorprecioList();
            List<FactorPrecio> tbFactorprecioListNew = claseVuelo.getTbFactorprecioList();
            List<Operacion> tbOperacionListOld = persistentClaseVuelo.getTbOperacionList();
            List<Operacion> tbOperacionListNew = claseVuelo.getTbOperacionList();
            List<FactorPrecio> attachedTbFactorprecioListNew = new ArrayList<FactorPrecio>();
            for (FactorPrecio tbFactorprecioListNewFactorPrecioToAttach : tbFactorprecioListNew) {
                tbFactorprecioListNewFactorPrecioToAttach = em.getReference(tbFactorprecioListNewFactorPrecioToAttach.getClass(), tbFactorprecioListNewFactorPrecioToAttach.getNIdfactor());
                attachedTbFactorprecioListNew.add(tbFactorprecioListNewFactorPrecioToAttach);
            }
            tbFactorprecioListNew = attachedTbFactorprecioListNew;
            claseVuelo.setTbFactorprecioList(tbFactorprecioListNew);
            List<Operacion> attachedTbOperacionListNew = new ArrayList<Operacion>();
            for (Operacion tbOperacionListNewOperacionToAttach : tbOperacionListNew) {
                tbOperacionListNewOperacionToAttach = em.getReference(tbOperacionListNewOperacionToAttach.getClass(), tbOperacionListNewOperacionToAttach.getNIdoperacion());
                attachedTbOperacionListNew.add(tbOperacionListNewOperacionToAttach);
            }
            tbOperacionListNew = attachedTbOperacionListNew;
            claseVuelo.setTbOperacionList(tbOperacionListNew);
            claseVuelo = em.merge(claseVuelo);
            for (FactorPrecio tbFactorprecioListOldFactorPrecio : tbFactorprecioListOld) {
                if (!tbFactorprecioListNew.contains(tbFactorprecioListOldFactorPrecio)) {
                    tbFactorprecioListOldFactorPrecio.setNClase(null);
                    tbFactorprecioListOldFactorPrecio = em.merge(tbFactorprecioListOldFactorPrecio);
                }
            }
            for (FactorPrecio tbFactorprecioListNewFactorPrecio : tbFactorprecioListNew) {
                if (!tbFactorprecioListOld.contains(tbFactorprecioListNewFactorPrecio)) {
                    ClaseVuelo oldNClaseOfTbFactorprecioListNewFactorPrecio = tbFactorprecioListNewFactorPrecio.getNClase();
                    tbFactorprecioListNewFactorPrecio.setNClase(claseVuelo);
                    tbFactorprecioListNewFactorPrecio = em.merge(tbFactorprecioListNewFactorPrecio);
                    if (oldNClaseOfTbFactorprecioListNewFactorPrecio != null && !oldNClaseOfTbFactorprecioListNewFactorPrecio.equals(claseVuelo)) {
                        oldNClaseOfTbFactorprecioListNewFactorPrecio.getTbFactorprecioList().remove(tbFactorprecioListNewFactorPrecio);
                        oldNClaseOfTbFactorprecioListNewFactorPrecio = em.merge(oldNClaseOfTbFactorprecioListNewFactorPrecio);
                    }
                }
            }
            for (Operacion tbOperacionListOldOperacion : tbOperacionListOld) {
                if (!tbOperacionListNew.contains(tbOperacionListOldOperacion)) {
                    tbOperacionListOldOperacion.setNClase(null);
                    tbOperacionListOldOperacion = em.merge(tbOperacionListOldOperacion);
                }
            }
            for (Operacion tbOperacionListNewOperacion : tbOperacionListNew) {
                if (!tbOperacionListOld.contains(tbOperacionListNewOperacion)) {
                    ClaseVuelo oldNClaseOfTbOperacionListNewOperacion = tbOperacionListNewOperacion.getNClase();
                    tbOperacionListNewOperacion.setNClase(claseVuelo);
                    tbOperacionListNewOperacion = em.merge(tbOperacionListNewOperacion);
                    if (oldNClaseOfTbOperacionListNewOperacion != null && !oldNClaseOfTbOperacionListNewOperacion.equals(claseVuelo)) {
                        oldNClaseOfTbOperacionListNewOperacion.getTbOperacionList().remove(tbOperacionListNewOperacion);
                        oldNClaseOfTbOperacionListNewOperacion = em.merge(oldNClaseOfTbOperacionListNewOperacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = claseVuelo.getNIdclase();
                if (findClaseVuelo(id) == null) {
                    throw new NonexistentEntityException("The claseVuelo with id " + id + " no longer exists.");
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
    public void destroy(BigDecimal id) throws NonexistentEntityException {
        try {
            em.getTransaction().begin();
            ClaseVuelo claseVuelo;
            try {
                claseVuelo = em.getReference(ClaseVuelo.class, id);
                claseVuelo.getNIdclase();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The claseVuelo with id " + id + " no longer exists.", enfe);
            }
            List<FactorPrecio> tbFactorprecioList = claseVuelo.getTbFactorprecioList();
            for (FactorPrecio tbFactorprecioListFactorPrecio : tbFactorprecioList) {
                tbFactorprecioListFactorPrecio.setNClase(null);
                tbFactorprecioListFactorPrecio = em.merge(tbFactorprecioListFactorPrecio);
            }
            List<Operacion> tbOperacionList = claseVuelo.getTbOperacionList();
            for (Operacion tbOperacionListOperacion : tbOperacionList) {
                tbOperacionListOperacion.setNClase(null);
                tbOperacionListOperacion = em.merge(tbOperacionListOperacion);
            }
            em.remove(claseVuelo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public List<ClaseVuelo> findClaseVueloEntities() {
        return findClaseVueloEntities(true, -1, -1);
    }
    
    @Override
    public List<ClaseVuelo> findClaseVueloEntities(int maxResults, int firstResult) {
        return findClaseVueloEntities(false, maxResults, firstResult);
    }

    private List<ClaseVuelo> findClaseVueloEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClaseVuelo.class));
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
    public ClaseVuelo findClaseVuelo(BigDecimal id) {
        try {
            return em.find(ClaseVuelo.class, id);
        } finally {
            em.close();
        }
    }
    
    @Override
    public int getClaseVueloCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClaseVuelo> rt = cq.from(ClaseVuelo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
