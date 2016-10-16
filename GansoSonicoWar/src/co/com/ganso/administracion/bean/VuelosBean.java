package co.com.ganso.administracion.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.ganso.entities.Vuelo;
import co.com.ganso.nucleo.bean.BackingUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class VuelosBean extends BackingUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8918479798475119956L;

	@EJB
	private IManagerSvc managerSvc;
	private List<Vuelo> listadoVuelos;

	@PostConstruct
	public void init() {
		try {
			cargarVuelos();
		} catch (Exception e) {
			dialogError(e);
		}
	}

	private void cargarVuelos() throws Exception {
		listadoVuelos = managerSvc.findAll(Vuelo.class);
	}

	public List<Vuelo> getListadoVuelos() {
		return listadoVuelos;
	}

	public void setListadoVuelos(List<Vuelo> listadoVuelos) {
		this.listadoVuelos = listadoVuelos;
	}
}
