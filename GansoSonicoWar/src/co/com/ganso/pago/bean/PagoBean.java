package co.com.ganso.pago.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import co.com.ganso.entities.Pago;
import co.com.ganso.entities.Pais;
import co.com.ganso.nucleo.bean.BackingUI;
import co.com.ganso.services.bussiness.IManagerSvc;

@ManagedBean
@ViewScoped
public class PagoBean extends BackingUI {

	private static final long serialVersionUID = 8918479798475119956L;

	@EJB
	private IManagerSvc managerSvc;
	private Pago pago;
	private String nombre;
	private String apellido;
	private String numDoc;
	private String telefono;
	private String franquicia;
	private String direccion;
	private String pais;
	private String codigoPostal;
	private List<SelectItem> listaMesFinal;
	private List<SelectItem> listaAnoFinal;
	private List<SelectItem> listaPaisFinal;
	private List<SelectItem> listaFranquiciaFinal;

	private List<Pais> listadoPaises;
	private List<String> listadoMes;
	private List<String> listadoAno;

	@PostConstruct
	public void init() {
		try {
			this.listadoPaises = new ArrayList<Pais>();
			this.listadoMes = new ArrayList<String>();
			this.listadoAno = new ArrayList<String>();

			this.listaMesFinal = new ArrayList<SelectItem>();
			this.listaAnoFinal = new ArrayList<SelectItem>();
			this.listaPaisFinal = new ArrayList<SelectItem>();
			this.listaFranquiciaFinal = new ArrayList<SelectItem>();

			cargarPaises();
			cargarMeses();
			cargarAnos();
			cargarFranquicias();
		} catch (Exception e) {
			dialogError(e);
		}
	}

	public void inicializar(Pago pago) {
		try {
			this.pago = pago;
		} catch (Exception e) {
			dialogError(e);
		}
	}

	private void cargarPaises() throws Exception {
		listadoPaises = managerSvc.findList(new Pais(), "Pais.findAll");

		for (Pais paisVal : listadoPaises) {
			listaPaisFinal.add(new SelectItem(paisVal.getTCodigo(), paisVal.getTNombre()));
		}
	}

	private void cargarMeses() throws Exception {
		for (int i = 1; i < 13; i++) {
			if (i <= 9) {
				listadoMes.add("0" + i);
			} else {
				listadoMes.add(String.valueOf(i));
			}
		}

		for (String val : listadoMes) {
			listaMesFinal.add(new SelectItem(val, val));
		}
	}

	private void cargarAnos() throws Exception {
		int year = 17;
		for (int i = 0; i < 10; i++) {
			int value = year + i;
			listadoAno.add(String.valueOf(value));
		}

		for (String val : listadoAno) {
			listaAnoFinal.add(new SelectItem(val, val));
		}
	}

	private void cargarFranquicias() {
		listaFranquiciaFinal.add(new SelectItem("1", "Visa"));
		listaFranquiciaFinal.add(new SelectItem("2", "Master Card"));
		listaFranquiciaFinal.add(new SelectItem("3", "Diners"));
		listaFranquiciaFinal.add(new SelectItem("4", "American Express"));
	}

	public String guardarPago() {
		try {
			pago.setTNombrepago(this.nombre);
			pago.setTApellidopago(this.apellido);
			pago.setTNumerodocumento(this.numDoc);
			pago.setTTelefonopago(this.telefono);
			pago.setTFranquicia(this.franquicia);
			pago.setTDireccion(this.direccion);
			pago.setTCodigoPais(this.pais);
			pago.setTCodigopostal(this.codigoPostal);

			managerSvc.create(pago);
			dialogInfo("Pago realizado correctamente");
			return "";
		} catch (Exception e) {
			dialogError(e);
			return "";
		}
	}

	public String regresarInicio() {
		return "../index.xhtml";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFranquicia() {
		return franquicia;
	}

	public void setFranquicia(String franquicia) {
		this.franquicia = franquicia;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public List<SelectItem> getListaMesFinal() {
		return listaMesFinal;
	}

	public List<SelectItem> getListaAnofinal() {
		return listaAnoFinal;
	}

	public List<SelectItem> getListaPaisFinal() {
		return listaPaisFinal;
	}

	public List<SelectItem> getListaFranquiciaFinal() {
		return listaFranquiciaFinal;
	}
}
