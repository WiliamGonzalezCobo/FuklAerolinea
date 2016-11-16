package co.com.ganso.nucleo.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import co.com.ganso.entities.Credencial;

public abstract class BackingUI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6364072497188239313L;
	
	public void error(String idcampo, String msjError) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msjError);
        FacesContext.getCurrentInstance().addMessage(idcampo, message);
	}
	
	public void warning(String idcampo, String msjAdvertencia) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", msjAdvertencia);
        FacesContext.getCurrentInstance().addMessage(idcampo, message);
	}
	
	public void info(String idcampo, String msjInfo) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msjInfo);
        FacesContext.getCurrentInstance().addMessage(idcampo, message);
	}
	
	public void dialogInfo(String mensaje) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", mensaje);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	public void dialogError(Exception e) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	public void dialogWarn(String mensaje) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", mensaje);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends BackingUI> T getBean(Class<T> clase) {
		String nombreBean = clase.getSimpleName();
		nombreBean = nombreBean.substring(0, 1).toLowerCase() + nombreBean.substring(1);
		return (T) FacesUtils.getBean(nombreBean);
	}
	
	public boolean isTieneCredencial(){
		if(getCredencial()==null){
			return false;
		}
		return true;
	}
	
	public Credencial getCredencial(){
		return (Credencial) SessionUtils.getParameterSession("credencial");
	}
	
	public void destroySession(){
		
	}
	
}
