package co.com.ganso.services.bussiness;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.EntityCore;
import co.com.ganso.entities.Usuario;
import co.com.ganso.services.bussinesslogic.exceptions.IllegalOrphanException;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

@Local
public interface IManagerSvc {

	public void create(EntityCore entityCore) throws PreexistingEntityException, Exception;

	public void update(EntityCore entityCore) throws IllegalOrphanException, NonexistentEntityException, Exception;

	public void delete(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException;

	public <T> List<T> findAll(Class<T> clase)throws Exception;

	public <T> List<T> findLimit(int maxResults, int firstResult, Class<T> clase)throws Exception;

	public <T> T findById(Class<T> clase, Object id)throws Exception;

	public int getEntityCoreCount()throws Exception;
	
	public boolean acceder(Usuario usuario)throws Exception;
}
