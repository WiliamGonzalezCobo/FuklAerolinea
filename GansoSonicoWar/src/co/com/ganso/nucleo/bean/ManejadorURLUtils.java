package co.com.ganso.nucleo.bean;

import java.util.ArrayList;
import java.util.List;

public class ManejadorURLUtils {
	
	public List<UrlUtils> getUrlsAdmin(){
		List<UrlUtils> urls = new ArrayList<UrlUtils>();
		urls.add(new UrlUtils("http://localhost:8180/gansosonico/admin/carga/cargarArchivo.jsf", "Cargar Archivo"));
		urls.add(new UrlUtils("http://localhost:8180/gansosonico/admin/vuelos/vuelos.jsf", "Vuelos"));
		urls.add(new UrlUtils("http://localhost:8180/gansosonico/admin/premios/premios.jsf", "Premios"));
		return urls;
	}
	
	public List<UrlUtils> getUrlsCliente(){
		List<UrlUtils> urls = new ArrayList<UrlUtils>();
		urls.add(new UrlUtils("http://localhost:8180/gansosonico/index.jsf", "Inicio"));
		urls.add(new UrlUtils("http://localhost:8180/gansosonico/cliente/activarPlanMillas.jsf", "Activar Plan de Millas"));
		
		return urls;
	}
	
}