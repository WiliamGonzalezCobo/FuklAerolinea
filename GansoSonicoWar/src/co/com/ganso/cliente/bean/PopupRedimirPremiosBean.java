package co.com.ganso.cliente.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.ganso.entities.PlanMilla;
import co.com.ganso.entities.Premio;
import co.com.ganso.nucleo.bean.BackingPopupUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class PopupRedimirPremiosBean extends BackingPopupUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1181616681704610303L;
	@EJB
	private IManagerSvc managerSvc;
	private Premio premio;
	private PlanMilla planMilla;

	@PostConstruct
	public void init() {
		try {

		} catch (Exception e) {
			dialogError(e);
		}
	}

	public void inicializar(String destino, Premio premio) {
		try {
			setDestino(destino);
			this.planMilla = new PlanMilla();
			this.premio = premio;
			mostrarPopup();
		} catch (Exception e) {
			dialogError(e);
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

	public void guardar() {
		try {
			Integer millasSobrantes = 0;
			if(validar()){
				millasSobrantes = planMilla.getNMillasacumuladas()-premio.getNValormillas();
				if(millasSobrantes >= 0){
					planMilla.setNMillasacumuladas(millasSobrantes);
					planMilla.setNMillasredimidas(premio.getNValormillas()+planMilla.getNMillasredimidas());
					managerSvc.update(planMilla);
					dialogInfo("Su Premio fue redimido");
					//getBean(RedimirPremiosBean.class).init();
					
				}else{
					millasSobrantes *=-1;
					dialogInfo("Necesita " + millasSobrantes + " millas adicionales para poder redimir el premio." );
				}
				aplicarDestino(null);
			}
		} catch (Exception e) {
			dialogError(e);
		}
	}

	public void eliminar() {
		try {
			managerSvc.delete(premio);
			dialogInfo("Se ha eliminado el registro.");
		} catch (Exception e) {
			dialogError(e);
		}
	}

	public Premio getPremio() {
		return premio;
	}

	public void setPremio(Premio premio) {
		this.premio = premio;
	}

	public PlanMilla getPlanMilla() {
		return planMilla;
	}

	public void setPlanMilla(PlanMilla planMilla) {
		this.planMilla = planMilla;
	}
}
