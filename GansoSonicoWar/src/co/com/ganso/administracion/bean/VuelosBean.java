package co.com.ganso.administracion.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

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
	private Vuelo vueloSeleccionado;

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
	
	public void nuevoVuelo(){
		try {
			getBean(PopupVuelosBean.class).inicializar("#{vuelosBean.refrescarVuelos}", new Vuelo());
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void verVuelo(SelectEvent se){
		try {
			getBean(PopupVuelosBean.class).inicializar("#{vuelosBean.refrescarVuelos}", vueloSeleccionado);
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void setRefrescarVuelos(Object object) throws Exception{
		cargarVuelos();
	}

	public List<Vuelo> getListadoVuelos() {
		return listadoVuelos;
	}

	public void setListadoVuelos(List<Vuelo> listadoVuelos) {
		this.listadoVuelos = listadoVuelos;
	}

	public Vuelo getVueloSeleccionado() {
		return vueloSeleccionado;
	}

	public void setVueloSeleccionado(Vuelo vueloSeleccionado) {
		this.vueloSeleccionado = vueloSeleccionado;
	}
}
