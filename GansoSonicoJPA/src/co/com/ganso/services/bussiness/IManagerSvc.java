package co.com.ganso.services.bussiness;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.EntityCore;
import co.com.ganso.services.bussinesslogic.exceptions.IllegalOrphanException;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

@Local
public interface IManagerSvc {
	
	public void create(EntityCore entityCore) throws PreexistingEntityException, Exception;

	public void edit(EntityCore entityCore) throws IllegalOrphanException, NonexistentEntityException, Exception;

	public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException;

	public List<EntityCore> findEntityCoreEntities();

	public List<EntityCore> findEntityCoreEntities(int maxResults, int firstResult);

	public EntityCore findEntityCore(BigDecimal id);

	public int getEntityCoreCount();
}
