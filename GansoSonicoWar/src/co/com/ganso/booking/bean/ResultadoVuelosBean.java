package co.com.ganso.booking.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	
	@PostConstruct
	public void init(){
		try {
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	private void cargarVuelos() throws Exception{
		VueloItinerario vueloItinerario = new VueloItinerario();
		vueloItinerario.setNItinerario(itinerario.getNIditinerario());
	}
	
	public void inicializar(Itinerario itinerario){
		this.itinerario = itinerario;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}
}
