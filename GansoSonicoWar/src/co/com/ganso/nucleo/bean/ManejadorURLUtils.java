package co.com.ganso.nucleo.bean;

import java.util.ArrayList;
import java.util.List;

public class ManejadorURLUtils {
	
	public List<UrlUtils> getUrlsAdmin(){
		List<UrlUtils> urls = new ArrayList<UrlUtils>();
		urls.add(new UrlUtils("/gansosonico/admin/carga/cargarArchivo.jsf", "Cargar Archivo"));
		urls.add(new UrlUtils("/gansosonico/admin/vuelos/vuelos.jsf", "Vuelos"));
		urls.add(new UrlUtils("/gansosonico/admin/premios/premios.jsf", "Premios"));
		urls.add(new UrlUtils("/gansosonico/admin/itinerario/itinerario.jsf", "Itinerarios"));
		return urls;
	}
	
	public List<UrlUtils> getUrlsCliente(){
		List<UrlUtils> urls = new ArrayList<UrlUtils>();
		urls.add(new UrlUtils("/gansosonico/index.jsf", "Inicio"));
		urls.add(new UrlUtils("/gansosonico/cliente/activarPlanMillas.jsf", "Activar Plan de Millas"));
		urls.add(new UrlUtils("/gansosonico/cliente/redimirPremios.jsf", "Redimir Premios"));
		return urls;
	}
	
}
