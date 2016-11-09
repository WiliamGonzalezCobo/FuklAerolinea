/*ManagerSvc*/
package co.com.ganso.services.bussinesslogic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.ganso.entities.Usuario;
import co.com.ganso.services.bussiness.IManagerSvc;
import co.com.ganso.services.bussiness.IUsuarioSvc;

@Stateless
public class UsuarioSvc implements IUsuarioSvc {

	@EJB
	private IManagerSvc managerSvc;

	@Override
	public boolean acceder(Usuario usuario) throws Exception{
		return managerSvc.findObject(usuario, "Usuario.findCredenciales")!= null;
					
		
	}

}
