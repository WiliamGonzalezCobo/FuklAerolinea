package co.com.ganso.administracion.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import co.com.ganso.entities.Premio;
import co.com.ganso.helper.cons.PremioCons;
import co.com.ganso.nucleo.bean.BackingUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class PremiosBean extends BackingUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8918479798475119956L;

	@EJB
	private IManagerSvc managerSvc;
	private List<Premio> listadoPremios;
	private Premio premiosSeleccionado;
	private Map<String, String> estado;

	@PostConstruct
	public void init() {
		try {
			estado = PremioCons.PREMIO_ESTADOS;
			cargarPremios();
		} catch (Exception e) {
			dialogError(e);
		}
	}

	private void cargarPremios() throws Exception {
		listadoPremios = managerSvc.findAll(Premio.class);
	}
	
	public void nuevoPremio(){
		try {
			getBean(PopupPremiosBean.class).inicializar("#{premiosBean.refrescarPremios}", new Premio());
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void verPremio(SelectEvent se){
		try {
			getBean(PopupPremiosBean.class).inicializar("#{premiosBean.refrescarPremios}", premiosSeleccionado);
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void setRefrescarPremios(Object object) throws Exception{
		cargarPremios();
	}

	public List<Premio> getListadoPremios() {
		return listadoPremios;
	}

	public void setListadoPremios(List<Premio> listadoPremios) {
		this.listadoPremios = listadoPremios;
	}

	public Premio getPremioSeleccionado() {
		return premiosSeleccionado;
	}

	public void setPremioSeleccionado(Premio premiosSeleccionado) {
		this.premiosSeleccionado = premiosSeleccionado;
	}

	public Map<String, String> getEstado() {
		return estado;
	}

	public void setEstado(Map<String, String> estado) {
		this.estado = estado;
	}
}
