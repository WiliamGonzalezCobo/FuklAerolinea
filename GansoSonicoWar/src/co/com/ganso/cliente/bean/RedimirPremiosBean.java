package co.com.ganso.cliente.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import co.com.ganso.entities.PlanMilla;
import co.com.ganso.entities.Premio;
import co.com.ganso.helper.cons.PremioCons;
import co.com.ganso.nucleo.bean.BackingUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class RedimirPremiosBean extends BackingUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8918479798475119956L;

	@EJB
	private IManagerSvc managerSvc;
	private List<Premio> listadoPremios;
	private Premio premiosSeleccionado;
	private Map<String, String> estado;
	private PlanMilla planMilla;

	@PostConstruct
	public void init() {
		try {
			estado = PremioCons.PREMIO_ESTADOS;
			
			cargarPremios();
			cargarMillas();
		} catch (Exception e) {
			dialogError(e);
		}
	}

	private void cargarPremios() throws Exception {
		listadoPremios = managerSvc.findList(new Premio(), "Premio.findAll");
	}
	
	private void  cargarMillas()throws Exception {
		
			planMilla = new PlanMilla();
			if(validar()){
			planMilla = managerSvc.findObject(planMilla,"PlanMilla.findIdPersona");
			}
			
	}
	private boolean validar(){
		try {
			boolean validador = true;
			if(isTieneCredencial()){
			planMilla.setnIdPersona(getCredencial().getIdentificacion());
			
			
			planMilla = managerSvc.findObject(planMilla,"PlanMilla.findIdPersona");
			if (planMilla.getNMillasacumuladas() == null){
				validador = false;
			} 
			}else{
				dialogInfo("Favor Inicie Sesion.");
			}
			return validador;	
		} catch (Exception e) {
			dialogError(e);
			return false;
			// TODO: handle exception
		}
		
		
	}

	
	public void nuevoPremio(){
		try {
			getBean(PopupRedimirPremiosBean.class).inicializar("#{redimirPremiosBean.refrescarPremios}", new Premio());
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void verPremio(SelectEvent se){
		try {
			getBean(PopupRedimirPremiosBean.class).inicializar("#{redimirPremiosBean.refrescarPremios}", premiosSeleccionado);
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void setRefrescarPremios(Object object) throws Exception{
		cargarPremios();
		cargarMillas();
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

	public PlanMilla getPlanMilla() {
		return planMilla;
	}

	public void setPlanMilla(PlanMilla planMilla) {
		this.planMilla = planMilla;
	}
}
