package co.com.ganso.services.bussiness;

import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.EntityCore;

@Local
public interface IManagerSvc {

	public void create(EntityCore entityCore) throws Exception;

	public void update(EntityCore entityCore) throws Exception;

	public void delete(EntityCore entityCore) throws Exception;

	public <T extends EntityCore> T findObject(EntityCore entity, String nameQuery) throws Exception;
	
	public <T extends EntityCore> List<T> findList(EntityCore entity, String nameQuery) throws Exception;
}
