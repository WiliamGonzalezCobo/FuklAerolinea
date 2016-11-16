package co.com.ganso.cliente.bean;

import java.io.Serializable;

import co.com.ganso.nucleo.bean.BackingUI;





import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.ganso.entities.Credencial;
import co.com.ganso.entities.Persona;
import co.com.ganso.entities.PlanMilla;
import co.com.ganso.entities.Usuario;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped

public class ActivarPlanMillas extends BackingUI implements Serializable {
	
	private static final long serialVersionUID = 8918479798475119956L;
	private PlanMilla planMilla;

	@EJB
	private IManagerSvc managerSvc;

	@PostConstruct
	public void init() {
		setPlanMilla(new PlanMilla());
	}

	public PlanMilla getPlanMilla() {
		return planMilla;
	}

	public void setPlanMilla(PlanMilla planMilla) {
		this.planMilla = planMilla;
	}
	
	public void activarPlanMillas(){
		try {
			if(isTieneCredencial()){
				planMilla.setnIdPersona(getCredencial().getIdentificacion());
				planMilla.setNMillasacumuladas(0);
				planMilla.setNMillasredimidas(0);
				managerSvc.create(planMilla);
				dialogInfo("Su Plan de Millas Fue Activado.");
			}	
		} catch (Exception e) {
			dialogInfo("Ya tiene Plan de millas Activo Felicitaciones");
		}
		
		
	}
	
}
