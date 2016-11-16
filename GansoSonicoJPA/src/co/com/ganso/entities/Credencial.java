package co.com.ganso.entities;

import java.io.Serializable;


/**
 * 
 * @author juanc
 *
 */
public class Credencial extends EntityCore implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2701670505936258316L;
	private String login;
	private String nombre;
	private String administrador;
	private String cliente;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAdministrador() {
		return administrador;
	}
	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
}