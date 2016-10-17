package co.com.ganso.administracion.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.ganso.entities.Premio;
import co.com.ganso.nucleo.bean.BackingPopupUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class PopupPremiosBean extends BackingPopupUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1181616681704610303L;
	@EJB
	private IManagerSvc managerSvc;
	private Premio premio;

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
			this.premio = premio;
			mostrarPopup();
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	private boolean validar(){
		boolean validador = true;
		
		return validador;
	}

	public void guardar() {
		try {
			if(validar()){
				if(premio.getNIdpremio()==null){
					managerSvc.create(premio);
					dialogInfo("Se ha guardado el registro.");
				}else{
					managerSvc.update(premio);
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
			managerSvc.delete(premio.getNIdpremio());
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
}
