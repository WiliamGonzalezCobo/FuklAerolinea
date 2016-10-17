package co.com.ganso.nucleo.bean;

import java.io.Serializable;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;

public abstract class BackingPopupUI extends BackingUI implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 3632484717697505277L;
	private String destino;
	private boolean visiblePopup;

	public void aplicarDestino(Object valor) {
		if (getDestino() == null || getDestino().isEmpty()) {
			return;
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext elContext = fc.getELContext();
		String expresion=null;
		if(getDestino().contains("#{")){
			expresion=getDestino();
		}else{
			expresion = "#{" + getDestino() + "}";
		}
		ValueExpression ve = fc.getApplication().getExpressionFactory().createValueExpression(elContext, expresion, Object.class);
		ve.setValue(elContext, valor);
	}
	
	public void ocultarAjax(CloseEvent ce){
		ocultarPopup();
	}
	
	public void mostrarPopup(){
		visiblePopup = true;
	}
	
	public void ocultarPopup(){
		visiblePopup = false;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public boolean isVisiblePopup() {
		return visiblePopup;
	}

	public void setVisiblePopup(boolean visiblePopup) {
		this.visiblePopup = visiblePopup;
	}	
}
