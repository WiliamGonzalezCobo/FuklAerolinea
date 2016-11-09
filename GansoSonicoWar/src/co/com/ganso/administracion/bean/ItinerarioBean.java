package co.com.ganso.administracion.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import co.com.ganso.entities.Itinerario;
import co.com.ganso.entities.VueloItinerario;
import co.com.ganso.nucleo.bean.BackingUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class ItinerarioBean extends BackingUI {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4450682259621902746L;
	@EJB
	private IManagerSvc managerSvc;
	private List<Itinerario> itinerarios;
	private Itinerario itinerarioSeleccionado;

	@PostConstruct
	public void init() {
		try {
			cargarItinerarios();
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void nuevoItinerario(){
		try {
			getBean(PopupItinerarioBean.class).inicializar("itinerarioBean.refrescarItinerarios", new Itinerario());
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void verItinerario(SelectEvent se){
		try {
			getBean(PopupItinerarioBean.class).inicializar("itinerarioBean.refrescarItinerarios", itinerarioSeleccionado);
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void setRefrescarItinerarios(Object object) throws Exception{
		cargarItinerarios();
	}

	private void cargarItinerarios() throws Exception {
		itinerarios = managerSvc.findList(new Itinerario(), "Itinerario.findAll");
	}
	
	public List<Itinerario> getItinerarios() {
		return itinerarios;
	}

	public void setItinerarios(List<Itinerario> itinerarios) {
		this.itinerarios = itinerarios;
	}

	public Itinerario getItinerarioSeleccionado() {
		return itinerarioSeleccionado;
	}

	public void setItinerarioSeleccionado(Itinerario itinerarioSeleccionado) {
		this.itinerarioSeleccionado = itinerarioSeleccionado;
	}
}
