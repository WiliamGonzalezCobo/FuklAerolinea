package co.com.ganso.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TB_OPERACION database table.
 * 
 */
@Entity
@Table(name="TB_OPERACION")
@NamedQuery(name="Operacion.findAll", query="SELECT o FROM Operacion o")
public class Operacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDOPERACION")
	private long nIdoperacion;

	@Temporal(TemporalType.DATE)
	@Column(name="D_FECHAOPERACION")
	private Date dFechaoperacion;

	@Column(name="N_CLASE")
	private java.math.BigDecimal nClase;

	@Column(name="N_ESTADO")
	private java.math.BigDecimal nEstado;

	@Column(name="N_PERSONA")
	private java.math.BigDecimal nPersona;

	@Column(name="N_VUELO")
	private java.math.BigDecimal nVuelo;

	public Operacion() {
	}

	public long getNIdoperacion() {
		return this.nIdoperacion;
	}

	public void setNIdoperacion(long nIdoperacion) {
		this.nIdoperacion = nIdoperacion;
	}

	public Date getDFechaoperacion() {
		return this.dFechaoperacion;
	}

	public void setDFechaoperacion(Date dFechaoperacion) {
		this.dFechaoperacion = dFechaoperacion;
	}

	public java.math.BigDecimal getNClase() {
		return this.nClase;
	}

	public void setNClase(java.math.BigDecimal nClase) {
		this.nClase = nClase;
	}

	public java.math.BigDecimal getNEstado() {
		return this.nEstado;
	}

	public void setNEstado(java.math.BigDecimal nEstado) {
		this.nEstado = nEstado;
	}

	public java.math.BigDecimal getNPersona() {
		return this.nPersona;
	}

	public void setNPersona(java.math.BigDecimal nPersona) {
		this.nPersona = nPersona;
	}

	public java.math.BigDecimal getNVuelo() {
		return this.nVuelo;
	}

	public void setNVuelo(java.math.BigDecimal nVuelo) {
		this.nVuelo = nVuelo;
	}

}