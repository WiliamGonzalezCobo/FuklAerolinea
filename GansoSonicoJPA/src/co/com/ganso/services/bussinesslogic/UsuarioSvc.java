/*ManagerSvc*/
package co.com.ganso.services.bussinesslogic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.ganso.entities.Credencial;
import co.com.ganso.entities.Usuario;
import co.com.ganso.services.bussiness.IManagerSvc;
import co.com.ganso.services.bussiness.IUsuarioSvc;

@Stateless
public class UsuarioSvc implements IUsuarioSvc {

	@EJB
	private IManagerSvc managerSvc;

	@Override
	public Credencial acceder(Usuario usuario) throws Exception{
		Credencial credencial = null;
		usuario = managerSvc.findObject(usuario, "Usuario.findCredenciales");
		if(usuario!= null){
			credencial = new Credencial();
			credencial.setLogin(usuario.getTLogin());
			credencial.setNombre(usuario.getPersona().getTNombres()+" "+usuario.getPersona().getTApellidos());
			credencial.setAdministrador(usuario.getTAdministrador());
			credencial.setCliente(usuario.getTCliente());
			credencial.setIdentificacion(usuario.getNPersona());
		};
		
		return credencial;
	}

}
