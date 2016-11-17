package co.com.ganso.administracion.bean;

import java.math.BigDecimal;
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
	private boolean nuevo;
	
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
			if(itinerario.getNIditinerario()==null){
				nuevo = true;
			}else{
				cargarEscalas();
			}
			mostrarPopup();
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	private void cargarEscalas() throws Exception{
		VueloItinerario buscarEscalas = new VueloItinerario();
		buscarEscalas.setNItinerario(itinerario.getNIditinerario());
		escalas = managerSvc.findList(buscarEscalas, "VueloItinerario.findEscalas");
	}
	
	private Integer calcularOrden(){
		return escalas.size()+1;
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
			getBean(PopupListaVueloBean.class).inicializar("popupItinerarioBean.vueloEscala", origenEscala());
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	private String origenEscala(){
		if(escalas.size()==0){
			return itinerario.getTOrigen();
		}
		return escalas.get(escalas.size()-1).getVuelo().getTDestino();
	}
	
	private boolean destinoEscala(){
		if(escalas==null||escalas.size()==0){
			return true;
		}
		return escalas.get(escalas.size()-1).getVuelo().getTDestino().equals(itinerario.getOrigen().getTCodigo());
	}
	
	public void setVueloEscala(Vuelo vuelo) throws Exception{
		escala.setNItinerario(itinerario.getNIditinerario());
		escala.setVuelo(vuelo);
		escala.setNVuelo(vuelo.getNIdvuelo());
		escala.setNOrden(new BigDecimal(calcularOrden()));
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
				ocultarPopup();
			}
		} catch (Exception e) {
			dialogError(e);
		}
	}

	public void eliminar() {
		try {
			managerSvc.delete(itinerario);
			dialogInfo("Se ha eliminado el registro.");
			ocultarPopup();
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	private boolean validarEscala(){
		boolean validador = true;
		
		return validador;
	}
	
	public void adicionarEscala(){
		try {
			if(validarEscala()){
				managerSvc.create(escala);
				dialogInfo("Se inserto la escala");
				cargarEscalas();
				destinoEscala();
			}
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

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}
	
	public boolean isItinerarioCompleto(){
		return destinoEscala();
	}
}
