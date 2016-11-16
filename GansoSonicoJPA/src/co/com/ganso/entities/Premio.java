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

	/**
	 * 
	 */
	private static final long serialVersionUID = -7579554409168842706L;

	@Id
	@Column(name="N_IDPREMIO")
	private BigDecimal nIdpremio;

	@Column(name="N_VALORMILLAS")
	private Integer nValormillas;

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

	public BigDecimal getNIdpremio() {
		return this.nIdpremio;
	}

	public void setNIdpremio(BigDecimal nIdpremio) {
		this.nIdpremio = nIdpremio;
	}

	public Integer getNValormillas() {
		return this.nValormillas;
	}

	public void setNValormillas(Integer nValormillas) {
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