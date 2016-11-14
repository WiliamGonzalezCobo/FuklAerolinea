package co.com.ganso.login.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import co.com.ganso.entities.Credencial;
import co.com.ganso.entities.Usuario;
import co.com.ganso.nucleo.bean.BackingUI;
import co.com.ganso.nucleo.bean.ManejadorURLUtils;
import co.com.ganso.nucleo.bean.SessionUtils;
import co.com.ganso.nucleo.bean.UrlUtils;
import co.com.ganso.services.bussiness.IUsuarioSvc;


@ManagedBean
@SessionScoped
public class LoginBean extends BackingUI implements Serializable {
	
	private static final long serialVersionUID = 8918479798475119956L;
	
	@EJB
	private IUsuarioSvc usuarioSvc;
	
	private Usuario usuario;
	private String password;
	private String username;
	private List<UrlUtils> urls;
	
	@PostConstruct
	public void init(){
		usuario = new Usuario();
	}

	public String accede() {
		try {
			usuario.setTLogin(username);
			usuario.setTPass(password);
			usuario.setTActivo("S");
			Credencial credencial = usuarioSvc.acceder(usuario);
			if (credencial!=null) {
				ManejadorURLUtils urlUtils = new ManejadorURLUtils();
				SessionUtils.setParameterSession("credencial", credencial);
				urls = new ArrayList<UrlUtils>();
				urls.addAll(urlUtils.getUrlsCliente());
				if("S".equals(credencial.getAdministrador())){
					urls.addAll(urlUtils.getUrlsAdmin());
				}
				return "index.xhtml";

			} else {
				dialogWarn("Credenciales Incorrectas.");
				return "index.xhtml";
			}
		} catch (Exception e) {
			dialogError(e);
			return "";
		}
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UrlUtils> getUrls() {
		return urls;
	}

	public void setUrls(List<UrlUtils> urls) {
		this.urls = urls;
	}
	
	public String salir(){
		SessionUtils.getSession().invalidate();
		return "index.jsf";
	}
	
}
