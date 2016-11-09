package co.com.ganso.administracion.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.ganso.entities.Aeropuerto;
import co.com.ganso.entities.Itinerario;
import co.com.ganso.entities.Vuelo;
import co.com.ganso.entities.VueloItinerario;
import co.com.ganso.nucleo.bean.BackingPopupUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class PopupItinerarioBean extends BackingPopupUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5352835502285929101L;
	@EJB
	private IManagerSvc managerSvc;
	private Itinerario itinerario;
	
	private VueloItinerario escala;
	private List<VueloItinerario> escalas;

	@PostConstruct
	public void init() {
		try {
			escala = new VueloItinerario();
		} catch (Exception e) {
			dialogError(e);
		}
	}

	public void inicializar(String destino, Itinerario itinerario) {
		try {
			setDestino(destino);
			this.itinerario = itinerario;
			mostrarPopup();
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void verOrigen(){
		try {
			verAeropuertos("popupItinerarioBean.aeropuertoOrigen");
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void verDestino(){
		try {
			verAeropuertos("popupItinerarioBean.aeropuertoDestino");
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	private void verAeropuertos(String destino) throws Exception{
		getBean(PopupAeropuertosBean.class).inicializar(destino);
	}
	
	public void setAeropuertoOrigen(Aeropuerto aeropuerto) throws Exception{
		itinerario.setTOrigen(aeropuerto.getTCodigo());
		itinerario.setOrigen(aeropuerto);
	}
	
	public void setAeropuertoDestino(Aeropuerto aeropuerto) throws Exception{
		itinerario.setTDestino(aeropuerto.getTCodigo());
		itinerario.setDestino(aeropuerto);
	}
	
	public void verVuelos(){
		try {
			getBean(PopupListaVueloBean.class).inicializar("popupItinerarioBean.vueloEscala", itinerario.getTOrigen());
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void setVueloEscala(Vuelo vuelo) throws Exception{
		escala.setNVuelo(vuelo.getNIdvuelo());
		escala.setVuelo(vuelo);
	}
	
	private boolean validar(){
		boolean validador = true;
		
		return validador;
	}

	public void guardar() {
		try {
			if(validar()){
				if(itinerario.getNIditinerario()==null){
					managerSvc.create(itinerario);
					dialogInfo("Se ha guardado el registro.");
				}else{
					managerSvc.update(itinerario);
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
			managerSvc.delete(itinerario.getNIditinerario());
			dialogInfo("Se ha eliminado el registro.");
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void adicionarEscala(){
		try {
			
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void eliminarEscala(){
		try {
			
		} catch (Exception e) {
			dialogError(e);
		}
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public VueloItinerario getEscala() {
		return escala;
	}

	public void setEscala(VueloItinerario escala) {
		this.escala = escala;
	}

	public List<VueloItinerario> getEscalas() {
		return escalas;
	}

	public void setEscalas(List<VueloItinerario> escalas) {
		this.escalas = escalas;
	}
}
