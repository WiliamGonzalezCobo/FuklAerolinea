package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TB_ESTADO database table.
 * 
 */
@Entity
@Table(name="TB_ESTADO")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDESTADO")
	private BigDecimal nIdestado;

	@Column(name="T_ACTIVO")
	private String tActivo;

	@Column(name="T_DESCRIPCION")
	private String tDescripcion;

	@Column(name="T_NOMBRE")
	private String tNombre;

	public Estado() {
	}

	public BigDecimal getNIdestado() {
		return this.nIdestado;
	}

	public void setNIdestado(BigDecimal nIdestado) {
		this.nIdestado = nIdestado;
	}

	public String getTActivo() {
		return this.tActivo;
	}

	public void setTActivo(String tActivo) {
		this.tActivo = tActivo;
	}

	public String getTDescripcion() {
		return this.tDescripcion;
	}

	public void setTDescripcion(String tDescripcion) {
		this.tDescripcion = tDescripcion;
	}

	public String getTNombre() {
		return this.tNombre;
	}

	public void setTNombre(String tNombre) {
		this.tNombre = tNombre;
	}

}