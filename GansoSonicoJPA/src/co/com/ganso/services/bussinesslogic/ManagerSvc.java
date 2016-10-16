package co.com.ganso.services.bussinesslogic;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import co.com.ganso.entities.EntityCore;
import co.com.ganso.services.bussiness.IManagerSvc;
import co.com.ganso.services.bussinesslogic.exceptions.IllegalOrphanException;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

@Stateless
public class ManagerSvc implements IManagerSvc {

	@PersistenceContext(unitName = JPACons.PersistenceUnit)
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
		EntityCore entityCore = em.getReference(EntityCore.class, id);
	}

	@Override
	public <T> List<T> findAll(Class<T> clase) {
		return findRegister(true, -1, -1, clase);
	}

	@Override
	public <T> List<T> findLimit(int maxResults, int firstResult, Class<T> clase) {
		return findRegister(false, maxResults, firstResult, clase);
	}

	private <T> List<T> findRegister(boolean all, int maxResults, int firstResult, Class<T> clase) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clase);
		Root<T> rootEntry = cq.from(clase);
		CriteriaQuery<T> todos = cq.select(rootEntry);
		TypedQuery<T> allQuery = em.createQuery(todos);
		return allQuery.getResultList();
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
