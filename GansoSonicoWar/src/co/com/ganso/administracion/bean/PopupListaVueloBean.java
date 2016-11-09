package co.com.ganso.administracion.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import co.com.ganso.entities.Vuelo;
import co.com.ganso.nucleo.bean.BackingPopupUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class PopupListaVueloBean extends BackingPopupUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7087890057269267088L;
	@EJB
	private IManagerSvc managerSvc;
	private Vuelo vueloSeleccionado;
	private List<Vuelo> vuelos;

	@PostConstruct
	public void init() {
		try {

		} catch (Exception e) {
			dialogError(e);
		}
	}

	public void inicializar(String destino) {
		try { 
			setDestino(destino);
			cargarVuelos();
			mostrarPopup();
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void inicializar(String destino, String origen) {
		try { 
			setDestino(destino);
			cargarEscalas(origen);
			mostrarPopup();
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	private void cargarVuelos() throws Exception{
		vuelos = managerSvc.findList(new Vuelo(), "Vuelo.findAll");
	}
	
	private void cargarEscalas(String origen) throws Exception{
		Vuelo vuelo = new Vuelo();
		vuelo.setTOrigen(origen);
		vuelos = managerSvc.findList(vuelo, "Vuelo.findEscalas");
	}
	
	public void seleccionar(SelectEvent se){
		aplicarDestino(vueloSeleccionado);
		ocultarPopup();
	}

	public Vuelo getVueloSeleccionado() {
		return vueloSeleccionado;
	}

	public void setVueloSeleccionado(Vuelo vueloSeleccionado) {
		this.vueloSeleccionado = vueloSeleccionado;
	}

	public List<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}
}
