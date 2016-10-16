package co.com.ganso.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TB_FACTORPRECIO database table.
 * 
 */
@Entity
@Table(name="TB_FACTORPRECIO")
@NamedQuery(name="FactorPrecio.findAll", query="SELECT f FROM FactorPrecio f")
public class FactorPrecio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDFACTOR")
	private long nIdfactor;

	@Column(name="N_CLASE")
	private BigDecimal nClase;

	@Column(name="N_FACTOR")
	private BigDecimal nFactor;

	@Column(name="T_DESCRIPCION")
	private String tDescripcion;

	public FactorPrecio() {
	}

	public long getNIdfactor() {
		return this.nIdfactor;
	}

	public void setNIdfactor(long nIdfactor) {
		this.nIdfactor = nIdfactor;
	}

	public BigDecimal getNClase() {
		return this.nClase;
	}

	public void setNClase(BigDecimal nClase) {
		this.nClase = nClase;
	}

	public BigDecimal getNFactor() {
		return this.nFactor;
	}

	public void setNFactor(BigDecimal nFactor) {
		this.nFactor = nFactor;
	}

	public String getTDescripcion() {
		return this.tDescripcion;
	}

	public void setTDescripcion(String tDescripcion) {
		this.tDescripcion = tDescripcion;
	}

}