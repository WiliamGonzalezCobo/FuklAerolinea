package co.com.ganso.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TB_USUARIO database table.
 * 
 */
@Entity
@Table(name="TB_USUARIO")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDUSUARIO")
	private long nIdusuario;

	@Column(name="N_PERSONA")
	private java.math.BigDecimal nPersona;

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

	public long getNIdusuario() {
		return this.nIdusuario;
	}

	public void setNIdusuario(long nIdusuario) {
		this.nIdusuario = nIdusuario;
	}

	public java.math.BigDecimal getNPersona() {
		return this.nPersona;
	}

	public void setNPersona(java.math.BigDecimal nPersona) {
		this.nPersona = nPersona;
	}

	public String getTActivo() {
		return this.tActivo;
	}

	public void setTActivo(String tActivo) {
		this.tActivo = tActivo;
	}

	public String getTCorreo() {
		return this.tCorreo;
	}

	public void setTCorreo(String tCorreo) {
		this.tCorreo = tCorreo;
	}

	public String getTLogin() {
		return this.tLogin;
	}

	public void setTLogin(String tLogin) {
		this.tLogin = tLogin;
	}

	public String getTPass() {
		return this.tPass;
	}

	public void setTPass(String tPass) {
		this.tPass = tPass;
	}

	public String getTAdministrador() {
		return tAdministrador;
	}

	public void setTAdministrador(String tAdministrador) {
		this.tAdministrador = tAdministrador;
	}

	public String getTCliente() {
		return tCliente;
	}

	public void setTCliente(String tCliente) {
		this.tCliente = tCliente;
	}

}