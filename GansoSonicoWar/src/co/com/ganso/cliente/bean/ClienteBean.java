package co.com.ganso.cliente.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.ganso.entities.Persona;
import co.com.ganso.entities.Usuario;
import co.com.ganso.nucleo.bean.BackingUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class ClienteBean extends BackingUI implements Serializable {
	
	private static final long serialVersionUID = 8918479798475119956L;
	private Persona persona;
	private Usuario usuario;
	private String confPass;
	@EJB
	private IManagerSvc managerSvc;

	@PostConstruct
	public void init() {
		setPersona(new Persona());
		setUsuario(new Usuario());
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfPass() {
		return confPass;
	}

	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}

	public boolean validaciones() {
		 if(usuario.getTLogin() != null && usuario.getTLogin() != "" ){
			return true;
		}
		else if(usuario.getTCorreo() != null && usuario.getTCorreo() != ""){
			return true;
		}
		else if(usuario.getTPass()!= "" && usuario.getTPass()!= null){
			if (usuario.getTPass().equals(confPass)) {
				return true;
			}
			else {return false;}
		}
		else if(persona.getDFechanacimiento() != null){
			return true;
		}
		else if(persona.getNIdpersona()!= null){
			return true;
		}
		else if(persona.getTApellidos()!= ""  && persona.getTApellidos()!= null){
			return true;
		}
		else if(persona.getTDireccion()!= "" && persona.getTDireccion()!= null){
			return true;
		}
		else if(persona.getTNombres()!= "" && persona.getTNombres()!= null){
			return true;
		}
		else if(persona.getTPasaporte()!= "" && persona.getTPasaporte()!= null){
			return true;
		}
		else if(persona.getTTelefono()!= "" && persona.getTTelefono()!= null){
			return true;
		}
		else {
			return false;
		}
	}

	public String crearPersona() {
		try {
			if(validaciones()){
				//usuario.setNIdusuario(persona.getNIdpersona());
				
				persona.setNCategoria(1);
				usuario.setNPersona(persona.getNIdpersona());
				usuario.setTActivo("S");
				usuario.setTAdministrador("N");
				usuario.setTCliente("S");
					managerSvc.create(persona);
					
					managerSvc.create(usuario);
					dialogInfo("Se ha guardado el registro.");
					return "../index.xhtml";
			}
			else{
				dialogInfo("Confirmar Datos");
				return "Cliente/registrar.xhtml";
			}
			
		} catch (Exception e) {
			String prueba = e.toString();
			dialogError(e);
			return "Cliente/registrar.xhtml";
		}

	}

}
