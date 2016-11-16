package co.com.ganso.services.bussiness;

import javax.ejb.Local;

import co.com.ganso.entities.Credencial;
import co.com.ganso.entities.Usuario;

@Local
public interface IUsuarioSvc { 
	
	public Credencial acceder(Usuario usuario) throws Exception;

}
