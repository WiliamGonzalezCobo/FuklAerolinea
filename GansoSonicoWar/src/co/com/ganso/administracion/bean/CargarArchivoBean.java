package co.com.ganso.administracion.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import co.com.ganso.archivo.ArchivoJson;
import co.com.ganso.entities.Premio;
import co.com.ganso.entities.Vuelo;
import co.com.ganso.nucleo.bean.BackingUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class CargarArchivoBean extends BackingUI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4055696894278332331L;
	@EJB
	private IManagerSvc managerSvc;

	private UploadedFile archivoVuelo;
	private UploadedFile archivoPremio;
	
	@PostConstruct
	public void init(){
		
	}

	public void cargarArchivoVuelo(FileUploadEvent event) {
		try {
			this.archivoVuelo = event.getFile();
			StringBuilder datos = ArchivoJson.cargar(archivoVuelo.getInputstream());
			List<Vuelo> listaVuelos = ArchivoJson.leer(datos, Vuelo.class);
			for (Vuelo vuelo : listaVuelos) {
				managerSvc.create(vuelo);
			}
			dialogInfo("Archivo Cargado");
			this.archivoVuelo = null;
		} catch (Exception e) {
			dialogError(e);
		}
	}
	
	public void cargarArchivoPremio(FileUploadEvent event) {
		try {
			this.archivoPremio = event.getFile();
			StringBuilder datos = ArchivoJson.cargar(archivoPremio.getInputstream());
			List<Premio> listaPremios = ArchivoJson.leer(datos, Premio.class);
			for (Premio premio : listaPremios) {
				managerSvc.create(premio);
			}
			dialogInfo("Archivo Cargado");
			this.archivoPremio = null;
		} catch (Exception e) {
			dialogError(e);
		}
	}

	public UploadedFile getArchivoVuelo() {
		return archivoVuelo;
	}

	public void setArchivoVuelo(UploadedFile archivoVuelo) {
		this.archivoVuelo = archivoVuelo;
	}

	public UploadedFile getArchivoPremio() {
		return archivoPremio;
	}

	public void setArchivoPremio(UploadedFile archivoPremio) {
		this.archivoPremio = archivoPremio;
	}	
}
