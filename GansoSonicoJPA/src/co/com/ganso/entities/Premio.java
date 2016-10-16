package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TB_PREMIO database table.
 * 
 */
@Entity
@Table(name="TB_PREMIO")
@NamedQuery(name="Premio.findAll", query="SELECT p FROM Premio p")
public class Premio extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDPREMIO")
	private long nIdpremio;

	@Column(name="N_VALORMILLAS")
	private BigDecimal nValormillas;

	@Column(name="T_ACTIVO")
	private String tActivo;

	@Column(name="T_DESCRIPCION")
	private String tDescripcion;

	@Column(name="T_NOMBREPREMIO")
	private String tNombrepremio;

	@Column(name="T_RUTAIMAGEN")
	private String tRutaimagen;

	public Premio() {
	}

	public long getNIdpremio() {
		return this.nIdpremio;
	}

	public void setNIdpremio(long nIdpremio) {
		this.nIdpremio = nIdpremio;
	}

	public BigDecimal getNValormillas() {
		return this.nValormillas;
	}

	public void setNValormillas(BigDecimal nValormillas) {
		this.nValormillas = nValormillas;
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

	public String getTNombrepremio() {
		return this.tNombrepremio;
	}

	public void setTNombrepremio(String tNombrepremio) {
		this.tNombrepremio = tNombrepremio;
	}

	public String getTRutaimagen() {
		return this.tRutaimagen;
	}

	public void setTRutaimagen(String tRutaimagen) {
		this.tRutaimagen = tRutaimagen;
	}

}