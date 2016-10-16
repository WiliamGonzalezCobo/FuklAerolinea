package co.com.ganso.services.bussinesslogic;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import co.com.ganso.entities.EntityCore;
import co.com.ganso.services.bussiness.IManagerSvc;
import co.com.ganso.services.bussinesslogic.exceptions.IllegalOrphanException;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

@Stateless
public class ManagerSvc implements IManagerSvc{
	
	@PersistenceContext(unitName=JPACons.PersistenceUnit)
    private EntityManager em;
	
	@Override
	public void create(EntityCore entityCore) throws PreexistingEntityException, Exception {
		em.persist(entityCore);
	}

	@Override
	public void edit(EntityCore entityCore) throws IllegalOrphanException, NonexistentEntityException, Exception {
		em.merge(entityCore);
	}

	@Override
	public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityCore entityCore =  em.getReference(EntityCore.class, id);;
        em.remove(entityCore);
	}

	@Override
	public List<EntityCore> findEntityCoreEntities() {
		return findEntityCoreEntities(true, -1, -1);
	}

	@Override
	public List<EntityCore> findEntityCoreEntities(int maxResults, int firstResult) {
		return findEntityCoreEntities(false, maxResults, firstResult);
	}

	 private List<EntityCore> findEntityCoreEntities(boolean all, int maxResults, int firstResult) {
	        try {
	            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
	            cq.select(cq.from(EntityCore.class));
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
	    public EntityCore findEntityCore(BigDecimal id) {
	        try {
	            return em.find(EntityCore.class, id);
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public int getEntityCoreCount() {
	        try {
	            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
	            Root<EntityCore> rt = cq.from(EntityCore.class);
	            cq.select(em.getCriteriaBuilder().count(rt));
	            Query q = em.createQuery(cq);
	            return ((Long) q.getSingleResult()).intValue();
	        } finally {
	            em.close();
	        }
	    }	
	
}
