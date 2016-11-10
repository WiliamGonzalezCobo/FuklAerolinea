package co.com.ganso.services.bussiness;

import javax.ejb.Local;

import co.com.ganso.entities.Usuario;

@Local
public interface IUsuarioSvc {
	
	public boolean acceder(Usuario usuario) throws Exception;

}
