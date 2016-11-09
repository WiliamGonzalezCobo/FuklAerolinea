package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.ganso.services.bussinesslogic.AnotacionParametro;


/**
 * The persistent class for the TB_USUARIO database table.
 * 
 */
@Entity
@Table(name="TB_USUARIO")
@NamedQueries({
@NamedQuery(name="Usuario.findCredenciales", query=Usuario.FIND_CREDENCIALES),
@NamedQuery(name="Usuario.findAll", query=Usuario.FIND_ALL)})
public class Usuario extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDUSUARIO")
	private BigDecimal nIdusuario;

	@Column(name="N_PERSONA")
	private BigDecimal nPersona;

	@Column(name="T_ACTIVO")
	private String tActivo;

	@Column(name="T_CORREO")
	private String tCorreo;

	@Column(name="T_LOGIN")
	private String tLogin;

	@Column(name="T_PASS")
	private String tPass;
	
	@Column(name="T_ADMINISTRADOR")
	private String tAdministrador;
	
	@Column(name="T_CLIENTE")
	private String tCliente;

	public Usuario() {
	}
	
	@AnotacionParametro
	public BigDecimal getNIdusuario() {
		return this.nIdusuario;
	}

	public void setNIdusuario(BigDecimal nIdusuario) {
		this.nIdusuario = nIdusuario;
	}
	
	@AnotacionParametro
	public BigDecimal getNPersona() {
		return this.nPersona;
	}

	public void setNPersona(java.math.BigDecimal nPersona) {
		this.nPersona = nPersona;
	}

	@AnotacionParametro
	public String getTActivo() {
		return this.tActivo;
	}

	public void setTActivo(String tActivo) {
		this.tActivo = tActivo;
	}

	@AnotacionParametro
	public String getTCorreo() {
		return this.tCorreo;
	}

	public void setTCorreo(String tCorreo) {
		this.tCorreo = tCorreo;
	}

	@AnotacionParametro
	public String getTLogin() {
		return this.tLogin;
	}

	public void setTLogin(String tLogin) {
		this.tLogin = tLogin;
	}

	@AnotacionParametro
	public String getTPass() {
		return this.tPass;
	}

	public void setTPass(String tPass) {
		this.tPass = tPass;
	}

	@AnotacionParametro
	public String getTAdministrador() {
		return tAdministrador;
	}

	public void setTAdministrador(String tAdministrador) {
		this.tAdministrador = tAdministrador;
	}

	@AnotacionParametro
	public String getTCliente() {
		return tCliente;
	}

	public void setTCliente(String tCliente) {
		this.tCliente = tCliente;
	}
	
	protected static final String FIND_ALL = "SELECT u FROM Usuario u";
	
	protected static final String FIND_CREDENCIALES = "SELECT u "
												    + "FROM Usuario u "
												    + "WHERE u.tLogin = :tLogin "
												  		   + "and u.tPass = :tPass "
												  		   + "and u.tAdministrador = :tAdministrador"; 

}