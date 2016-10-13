/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussinesslogic;

import java.math.BigDecimal;
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
import co.com.ganso.services.bussiness.IFactorPrecioLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Stateless
public class FactorPrecioSvc implements IFactorPrecioLocalSvc {

	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;
    
    @Override
    public void create(FactorPrecio factorPrecio) throws PreexistingEntityException, Exception {
        try {
            em.getTransaction().begin();
            ClaseVuelo NClase = factorPrecio.getNClase();
            if (NClase != null) {
                NClase = em.getReference(NClase.getClass(), NClase.getNIdclase());
                factorPrecio.setNClase(NClase);
            }
            em.persist(factorPrecio);
            if (NClase != null) {
                NClase.getTbFactorprecioList().add(factorPrecio);
                NClase = em.merge(NClase);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFactorPrecio(factorPrecio.getNIdfactor()) != null) {
                throw new PreexistingEntityException("FactorPrecio " + factorPrecio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public void edit(FactorPrecio factorPrecio) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            FactorPrecio persistentFactorPrecio = em.find(FactorPrecio.class, factorPrecio.getNIdfactor());
            ClaseVuelo NClaseOld = persistentFactorPrecio.getNClase();
            ClaseVuelo NClaseNew = factorPrecio.getNClase();
            if (NClaseNew != null) {
                NClaseNew = em.getReference(NClaseNew.getClass(), NClaseNew.getNIdclase());
                factorPrecio.setNClase(NClaseNew);
            }
            factorPrecio = em.merge(factorPrecio);
            if (NClaseOld != null && !NClaseOld.equals(NClaseNew)) {
                NClaseOld.getTbFactorprecioList().remove(factorPrecio);
                NClaseOld = em.merge(NClaseOld);
            }
            if (NClaseNew != null && !NClaseNew.equals(NClaseOld)) {
                NClaseNew.getTbFactorprecioList().add(factorPrecio);
                NClaseNew = em.merge(NClaseNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = factorPrecio.getNIdfactor();
                if (findFactorPrecio(id) == null) {
                    throw new NonexistentEntityException("The factorPrecio with id " + id + " no longer exists.");
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
            FactorPrecio factorPrecio;
            try {
                factorPrecio = em.getReference(FactorPrecio.class, id);
                factorPrecio.getNIdfactor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factorPrecio with id " + id + " no longer exists.", enfe);
            }
            ClaseVuelo NClase = factorPrecio.getNClase();
            if (NClase != null) {
                NClase.getTbFactorprecioList().remove(factorPrecio);
                NClase = em.merge(NClase);
            }
            em.remove(factorPrecio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public List<FactorPrecio> findFactorPrecioEntities() {
        return findFactorPrecioEntities(true, -1, -1);
    }

    @Override
    public List<FactorPrecio> findFactorPrecioEntities(int maxResults, int firstResult) {
        return findFactorPrecioEntities(false, maxResults, firstResult);
    }

    private List<FactorPrecio> findFactorPrecioEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FactorPrecio.class));
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
    public FactorPrecio findFactorPrecio(BigDecimal id) {
        try {
            return em.find(FactorPrecio.class, id);
        } finally {
            em.close();
        }
    }
    
    @Override
    public int getFactorPrecioCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FactorPrecio> rt = cq.from(FactorPrecio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
