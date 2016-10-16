package co.com.ganso.administracion.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.ganso.entities.Premio;
import co.com.ganso.nucleo.bean.BackingUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class PremiosBean extends BackingUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8918479798475119956L;

	@EJB
	private IManagerSvc managerSvc;
	private List<Premio> listadoPremios;

	@PostConstruct
	public void init() {
		try {
			cargarPremios();
		} catch (Exception e) {
			dialogError(e);
		}
	}

	private void cargarPremios() throws Exception {
		listadoPremios = managerSvc.findAll(Premio.class);
	}

	public List<Premio> getListadoPremios() {
		return listadoPremios;
	}

	public void setListadoPremios(List<Premio> listadoPremios) {
		this.listadoPremios = listadoPremios;
	}
}
