package co.com.ganso.nucleo.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static Object getParameterSession(String key) {
		HttpSession session = getSession();
		return session.getAttribute(key);
	}
	
	public static void setParameterSession(String key, Object objeto) {
		HttpSession session = getSession();
		session.setAttribute(key, objeto);
	}
	
	public static void setRemoveParameterSession(String key) {
		HttpSession session = getSession();
		session.removeAttribute(key);
	}
}