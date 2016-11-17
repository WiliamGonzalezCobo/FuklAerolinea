package co.com.ganso.administracion.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.ganso.entities.Aeropuerto;
import co.com.ganso.entities.Vuelo;
import co.com.ganso.nucleo.bean.BackingPopupUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class PopupVuelosBean extends BackingPopupUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1181616681704610303L;
	@EJB
	private IManagerSvc managerSvc;
	private Vuelo vuelo;

	@PostConstruct
	public void init() {
		try {

		} catch (Exception e) {
			dialogError(e);
		}
	}

	public void inicializar(String destino, Vuelo vuelo) {
		try {
			setDestino(destino);
			this.vuelo = vuelo;
			mostrarPopup();
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void verOrigen(){
		try {
			verAeropuertos("popupVuelosBean.aeropuertoOrigen");
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void verDestino(){
		try {
			verAeropuertos("popupVuelosBean.aeropuertoDestino");
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	private void verAeropuertos(String destino) throws Exception{
		getBean(PopupAeropuertosBean.class).inicializar(destino);
	}
	
	public void setAeropuertoOrigen(Aeropuerto aeropuerto) throws Exception{
		vuelo.setTOrigen(aeropuerto.getTCodigo());
		vuelo.setOrigen(aeropuerto);
	}
	
	public void setAeropuertoDestino(Aeropuerto aeropuerto) throws Exception{
		vuelo.setTDestino(aeropuerto.getTCodigo());
		vuelo.setDestino(aeropuerto);
	}
	
	private boolean validar(){
		boolean validador = true;
		
		return validador;
	}

	public void guardar() {
		try {
			if(validar()){
				if(vuelo.getNIdvuelo()==null){
					managerSvc.create(vuelo);
					dialogInfo("Se ha guardado el registro.");
				}else{
					managerSvc.update(vuelo);
					dialogInfo("Se ha actualizado el registro.");
				}
				aplicarDestino(null);
			}
		} catch (Exception e) {
			dialogError(e);
		}
	}

	public void eliminar() {
		try {
			managerSvc.delete(vuelo);
			dialogInfo("Se ha eliminado el registro.");
		} catch (Exception e) {
			dialogError(e);
		}
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
}
