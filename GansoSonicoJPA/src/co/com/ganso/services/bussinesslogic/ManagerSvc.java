package co.com.ganso.services.bussinesslogic;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.ganso.entities.EntityCore;
import co.com.ganso.services.bussiness.IManagerSvc;
import co.com.ganso.services.bussinesslogic.exceptions.IllegalOrphanException;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;
import java.sql.*; 

@Stateless
public class ManagerSvc implements IManagerSvc {

	@PersistenceContext(unitName = JPACons.PersistenceUnit)
	private EntityManager em;

	@Override
	public void create(EntityCore entityCore) throws SQLException, PreexistingEntityException, Exception {
		em.persist(entityCore);
	}

	@Override
	public void update(EntityCore entityCore) throws IllegalOrphanException, NonexistentEntityException, Exception {
		em.merge(entityCore);
	}

	@Override
	public void delete(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
		em.getReference(EntityCore.class, id);
	}

//	@Override
//	public <T> List<T> findAll(Class<T> clase) {
//		return findRegister(true, -1, -1, clase);
//	}

//	@Override
//	public <T> List<T> findLimit(int maxResults, int firstResult, Class<T> clase) {
//		return findRegister(false, maxResults, firstResult, clase);
//	}

//	private <T> List<T> findRegister(boolean all, int maxResults, int firstResult, Class<T> clase) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<T> cq = cb.createQuery(clase);
//		Root<T> rootEntry = cq.from(clase);
//		CriteriaQuery<T> todos = cq.select(rootEntry);
//		TypedQuery<T> allQuery = em.createQuery(todos)
//								   .setFirstResult(firstResult)
//								   .setMaxResults(maxResults);
//		return allQuery.getResultList();
//	}

//	@Override
//	public <T> T findById(Class<T> clase, Object id) throws Exception{
//		return em.find(clase, id);
//	}
	
	@SuppressWarnings("unchecked")
	public <T extends EntityCore> List<T> findList(EntityCore entity, String nameQuery) throws Exception{
		Map<String, Object> mapaParametros = buildMapParameters(entity);
		Query query = em.createNamedQuery(nameQuery);
		for (Entry<String, Object> entry : mapaParametros.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		List<T> list = query.getResultList();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends EntityCore> T findObject(EntityCore entity, String nameQuery) throws Exception {
		try {
			List<?> resultado = findList(entity, nameQuery);
			if (resultado.size() != 0) {
				return (T) resultado.get(0);
			}
		} catch (javax.persistence.NoResultException e) {
		}

		return null;
	}
	
	private Map<String, Object> buildMapParameters(EntityCore entity) throws Exception {
		Object objeto;
		Map<String, Object> mapaParametros = new HashMap<String, Object>();
		for (Method metodo : entity.getClass().getMethods()) {
			if (!metodo.getName().toUpperCase().startsWith("GET") || metodo.getAnnotation(AnotacionParametro.class) == null) {
				continue;
			}
			objeto = metodo.invoke(entity);
			if(objeto == null){
				continue;
			}
			mapaParametros.put(obtenerNombreCampo(metodo.getName()), objeto);
		}
		return mapaParametros;
	}
	
	private String obtenerNombreCampo(String name) {
		name = name.replaceAll("get", "");
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}
}
