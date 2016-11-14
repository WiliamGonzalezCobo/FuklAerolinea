package co.com.ganso.services.bussinesslogic;

import java.lang.reflect.Method;
import java.util.ArrayList;
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

@Stateless
public class ManagerSvc implements IManagerSvc {

	@PersistenceContext(unitName = JPACons.PersistenceUnit)
	private EntityManager em;

	@Override
	public void create(EntityCore entityCore) throws Exception {
		em.persist(entityCore);
	}

	@Override
	public void update(EntityCore entityCore) throws Exception {
		em.merge(entityCore);
	}

	@Override
	public void delete(EntityCore entityCore) throws Exception {
		update(entityCore);
		em.remove(entityCore);
	}

	@SuppressWarnings("unchecked")
	public <T extends EntityCore> List<T> findList(EntityCore entity, String nameQuery) throws Exception{
		Map<String, Object> mapaParametros = buildMapParameters(entity);
		Query query = em.createNamedQuery(nameQuery);
		for (Entry<String, Object> entry : mapaParametros.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		List<T> list = query.getResultList();
		if(list==null || list.size()==0){
			list = new ArrayList<T>();
		}
		
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
