package co.com.ganso.booking.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.ganso.entities.EntityCore;
import co.com.ganso.entities.Itinerario;
import co.com.ganso.entities.VueloItinerario;
import co.com.ganso.nucleo.bean.BackingUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class ResultadoVuelosBean extends BackingUI {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8242991927861253178L;
	@EJB
	private IManagerSvc managerSvc;
	private Itinerario itinerario;
	private List<VueloItinerario> escalas;
	private List<Itinerario> itinerarios;
	
	@PostConstruct
	public void init(){
		try {
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void inicializar(Itinerario itinerario){
		try {
			itinerarios = managerSvc.findList(itinerario, "Itinerario.findItinerarios");
			this.itinerario = itinerario;
			List<VueloItinerario> listaVuelos = null;
			VueloItinerario buscar = new VueloItinerario();
			for (Itinerario it : itinerarios) {
				buscar.setNItinerario(it.getNIditinerario());
				listaVuelos = managerSvc.findList(buscar, "VueloItinerario.findEscalas");
				it.setVuelos(listaVuelos);
			}
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void regresar(){
		try {
			navegar("REGRESAR_RESULTADO_VUELO");
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void comprar(){
		try {

		} catch (Exception e) {
			dialogError(e);
		}
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public List<VueloItinerario> getEscalas() {
		return escalas;
	}

	public List<Itinerario> getItinerarios() {
		return itinerarios;
	}

	public void setItinerarios(List<Itinerario> itinerarios) {
		this.itinerarios = itinerarios;
	}
}
