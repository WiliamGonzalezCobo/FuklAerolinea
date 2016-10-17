package co.com.ganso.administracion.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import co.com.ganso.entities.Aeropuerto;
import co.com.ganso.nucleo.bean.BackingPopupUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class PopupAeropuertosBean extends BackingPopupUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7087890057269267088L;
	@EJB
	private IManagerSvc managerSvc;
	private Aeropuerto aeropuertoSeleccionado;
	private List<Aeropuerto> aeropuertos;

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
			cargarAeropuertos();
			mostrarPopup();
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	private void cargarAeropuertos() throws Exception{
		aeropuertos = managerSvc.findAll(Aeropuerto.class);
	}
	
	public void seleccionar(SelectEvent se){
		aplicarDestino(aeropuertoSeleccionado);
		ocultarPopup();
	}

	public List<Aeropuerto> getAeropuertos() {
		return aeropuertos;
	}

	public void setAeropuertos(List<Aeropuerto> aeropuertos) {
		this.aeropuertos = aeropuertos;
	}

	public Aeropuerto getAeropuertoSeleccionado() {
		return aeropuertoSeleccionado;
	}

	public void setAeropuertoSeleccionado(Aeropuerto aeropuertoSeleccionado) {
		this.aeropuertoSeleccionado = aeropuertoSeleccionado;
	}
}
