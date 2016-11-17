package co.com.ganso.booking.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import co.com.ganso.administracion.bean.PopupAeropuertosBean;
import co.com.ganso.entities.Aeropuerto;
import co.com.ganso.entities.ClaseVuelo;
import co.com.ganso.entities.Itinerario;
import co.com.ganso.helper.cons.BookingCons;
import co.com.ganso.nucleo.bean.BackingUI;
import co.com.ganso.nucleo.bean.Convertidor;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class BookingBean extends BackingUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2305905920730600766L;
	@EJB
	private IManagerSvc managerSvc;
	
	private List<SelectItem> tiposTrayectos;
	private List<SelectItem> listaCantidades;
	private List<SelectItem> listaClase;
	
	private Itinerario itinerario;
	private String trayecto;
	
	@PostConstruct
	public void init(){
		try {
			setTrayecto(BookingCons.IDA_REGRESO);
			itinerario = new Itinerario();
			itinerario.setCantidadAdultos(0);
			itinerario.setCantidadNinos(0);
			itinerario.setCantidadBebes(0);
			cargarListas();
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	private void cargarListas() throws Exception{
		tiposTrayectos = Convertidor.convertirMapToListSelectItem(BookingCons.LISTA_TRAYECTO);
		listaCantidades = Convertidor.convertirMapIntegerStringToListSelectItem(BookingCons.cantidades);
		listaClase = new ArrayList<SelectItem>();
		List<ClaseVuelo> listadoClases = managerSvc.findList(new ClaseVuelo(), "ClaseVuelo.findAll");
		itinerario.setClase(listadoClases.get(0).getNIdclase());
		itinerario.setClaseDesc(listadoClases.get(0).getTNombre());
		for (ClaseVuelo claseVuelo : listadoClases) {
			listaClase.add(new SelectItem(claseVuelo.getNIdclase(), claseVuelo.getTNombre()));
		}
	}
	
	public void verOrigen(){
		try {
			verAeropuertos("bookingBean.aeropuertoOrigen");
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void verDestino(){
		try {
			verAeropuertos("bookingBean.aeropuertoDestino");
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
	
	private boolean validar(){
		boolean validador = true;
		validador &= itinerario.getOrigen()  != null;
		validador &= itinerario.getDestino() != null;
		validador &= itinerario.getDFecha()  != null;
		return validador;
	}
	
	public void buscar(){
		try {
			if(validar()){
				navegar("RESULTADO_VUELO");
				getBean(ResultadoVuelosBean.class).inicializar(itinerario);
			}else{
				dialogWarn("Debe llenar mas campos.");
			}
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void limpiar(){
		try {
			itinerario = new Itinerario();
			itinerario.setCantidadAdultos(0);
			itinerario.setCantidadNinos(0);
			itinerario.setCantidadBebes(0);
			cargarListas();
		} catch (Exception e) {
			dialogError(e);
		}
	}

	public List<SelectItem> getTiposTrayectos() {
		return tiposTrayectos;
	}

	public String getTrayecto() {
		return trayecto;
	}

	public void setTrayecto(String trayecto) {
		this.trayecto = trayecto;
	}
	
	public boolean isIdaRegreso(){
		return BookingCons.IDA_REGRESO.equals(getTrayecto());
	}

	public List<SelectItem> getListaCantidades() {
		return listaCantidades;
	}

	public List<SelectItem> getListaClase() {
		return listaClase;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}
}
