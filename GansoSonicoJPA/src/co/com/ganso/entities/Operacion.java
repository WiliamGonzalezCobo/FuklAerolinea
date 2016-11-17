package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TB_OPERACION database table.
 * 
 */
@Entity
@Table(name="TB_OPERACION")
@NamedQuery(name="Operacion.findAll", query="SELECT o FROM Operacion o")
public class Operacion extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDOPERACION")
	private BigDecimal nIdoperacion;

	@Temporal(TemporalType.DATE)
	@Column(name="D_FECHAOPERACION")
	private Date dFechaoperacion;

	@Column(name="N_CLASE")
	private BigDecimal nClase;

	@Column(name="N_ESTADO")
	private BigDecimal nEstado;

	@Column(name="N_PERSONA")
	private BigDecimal nPersona;

	@Column(name="N_VUELO")
	private BigDecimal nVuelo;
	
	@Column(name="N_VALORTOTAL")
	private BigDecimal nValorTotal;

	public Operacion() {
	}

	public BigDecimal getNIdoperacion() {
		return this.nIdoperacion;
	}

	public void setNIdoperacion(BigDecimal nIdoperacion) {
		this.nIdoperacion = nIdoperacion;
	}

	public Date getDFechaoperacion() {
		return this.dFechaoperacion;
	}

	public void setDFechaoperacion(Date dFechaoperacion) {
		this.dFechaoperacion = dFechaoperacion;
	}

	public BigDecimal getNClase() {
		return this.nClase;
	}

	public void setNClase(BigDecimal nClase) {
		this.nClase = nClase;
	}

	public BigDecimal getNEstado() {
		return this.nEstado;
	}

	public void setNEstado(BigDecimal nEstado) {
		this.nEstado = nEstado;
	}

	public BigDecimal getNPersona() {
		return this.nPersona;
	}

	public void setNPersona(BigDecimal nPersona) {
		this.nPersona = nPersona;
	}

	public BigDecimal getNVuelo() {
		return this.nVuelo;
	}

	public void setNVuelo(BigDecimal nVuelo) {
		this.nVuelo = nVuelo;
	}

	public BigDecimal getNValorTotal() {
		return nValorTotal;
	}

	public void setNValorTotal(BigDecimal nValorTotal) {
		this.nValorTotal = nValorTotal;
	}

}