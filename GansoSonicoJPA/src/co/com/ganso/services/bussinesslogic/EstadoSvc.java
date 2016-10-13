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

import co.com.ganso.entities.Estado;
import co.com.ganso.entities.Operacion;
import co.com.ganso.services.bussiness.IEstadoLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.IllegalOrphanException;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */

@Stateless
public class EstadoSvc implements IEstadoLocalSvc {

	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;
    
    @Override
    public void create(Estado estado) throws PreexistingEntityException, Exception {
        if (estado.getTbOperacionList() == null) {
            estado.setTbOperacionList(new ArrayList<Operacion>());
        }
        try {
            em.getTransaction().begin();
            List<Operacion> attachedTbOperacionList = new ArrayList<Operacion>();
            for (Operacion tbOperacionListOperacionToAttach : estado.getTbOperacionList()) {
                tbOperacionListOperacionToAttach = em.getReference(tbOperacionListOperacionToAttach.getClass(), tbOperacionListOperacionToAttach.getNIdoperacion());
                attachedTbOperacionList.add(tbOperacionListOperacionToAttach);
            }
            estado.setTbOperacionList(attachedTbOperacionList);
            em.persist(estado);
            for (Operacion tbOperacionListOperacion : estado.getTbOperacionList()) {
                Estado oldNEstadoOfTbOperacionListOperacion = tbOperacionListOperacion.getNEstado();
                tbOperacionListOperacion.setNEstado(estado);
                tbOperacionListOperacion = em.merge(tbOperacionListOperacion);
                if (oldNEstadoOfTbOperacionListOperacion != null) {
                    oldNEstadoOfTbOperacionListOperacion.getTbOperacionList().remove(tbOperacionListOperacion);
                    oldNEstadoOfTbOperacionListOperacion = em.merge(oldNEstadoOfTbOperacionListOperacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstado(estado.getNIdestado()) != null) {
                throw new PreexistingEntityException("Estado " + estado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public void edit(Estado estado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            Estado persistentEstado = em.find(Estado.class, estado.getNIdestado());
            List<Operacion> tbOperacionListOld = persistentEstado.getTbOperacionList();
            List<Operacion> tbOperacionListNew = estado.getTbOperacionList();
            List<String> illegalOrphanMessages = null;
            for (Operacion tbOperacionListOldOperacion : tbOperacionListOld) {
                if (!tbOperacionListNew.contains(tbOperacionListOldOperacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Operacion " + tbOperacionListOldOperacion + " since its NEstado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Operacion> attachedTbOperacionListNew = new ArrayList<Operacion>();
            for (Operacion tbOperacionListNewOperacionToAttach : tbOperacionListNew) {
                tbOperacionListNewOperacionToAttach = em.getReference(tbOperacionListNewOperacionToAttach.getClass(), tbOperacionListNewOperacionToAttach.getNIdoperacion());
                attachedTbOperacionListNew.add(tbOperacionListNewOperacionToAttach);
            }
            tbOperacionListNew = attachedTbOperacionListNew;
            estado.setTbOperacionList(tbOperacionListNew);
            estado = em.merge(estado);
            for (Operacion tbOperacionListNewOperacion : tbOperacionListNew) {
                if (!tbOperacionListOld.contains(tbOperacionListNewOperacion)) {
                    Estado oldNEstadoOfTbOperacionListNewOperacion = tbOperacionListNewOperacion.getNEstado();
                    tbOperacionListNewOperacion.setNEstado(estado);
                    tbOperacionListNewOperacion = em.merge(tbOperacionListNewOperacion);
                    if (oldNEstadoOfTbOperacionListNewOperacion != null && !oldNEstadoOfTbOperacionListNewOperacion.equals(estado)) {
                        oldNEstadoOfTbOperacionListNewOperacion.getTbOperacionList().remove(tbOperacionListNewOperacion);
                        oldNEstadoOfTbOperacionListNewOperacion = em.merge(oldNEstadoOfTbOperacionListNewOperacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = estado.getNIdestado();
                if (findEstado(id) == null) {
                    throw new NonexistentEntityException("The estado with id " + id + " no longer exists.");
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
            Estado estado;
            try {
                estado = em.getReference(Estado.class, id);
                estado.getNIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Operacion> tbOperacionListOrphanCheck = estado.getTbOperacionList();
            for (Operacion tbOperacionListOrphanCheckOperacion : tbOperacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estado (" + estado + ") cannot be destroyed since the Operacion " + tbOperacionListOrphanCheckOperacion + " in its tbOperacionList field has a non-nullable NEstado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public List<Estado> findEstadoEntities() {
        return findEstadoEntities(true, -1, -1);
    }
    
    @Override
    public List<Estado> findEstadoEntities(int maxResults, int firstResult) {
        return findEstadoEntities(false, maxResults, firstResult);
    }

    private List<Estado> findEstadoEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estado.class));
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
    public Estado findEstado(BigDecimal id) {
        try {
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }
    
    @Override
    public int getEstadoCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estado> rt = cq.from(Estado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
