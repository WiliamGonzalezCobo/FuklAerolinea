package co.com.ganso.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TB_PERSONAPREMIO database table.
 * 
 */
@Entity
@Table(name="TB_PERSONAPREMIO")
@NamedQuery(name="PersonaPremio.findAll", query="SELECT p FROM PersonaPremio p")
public class PersonaPremio extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDPERSONAPREMIO")
	private BigDecimal nIdpersonapremio;

	@Temporal(TemporalType.DATE)
	@Column(name="D_FECHACOMPRA")
	private Date dFechacompra;

	@Column(name="N_PERSONA")
	private BigDecimal nPersona;

	@Column(name="N_PREMIO")
	private BigDecimal nPremio;

	@Column(name="N_VALORMILLAS")
	private BigDecimal nValormillas;

	public PersonaPremio() {
	}

	public BigDecimal getNIdpersonapremio() {
		return this.nIdpersonapremio;
	}

	public void setNIdpersonapremio(BigDecimal nIdpersonapremio) {
		this.nIdpersonapremio = nIdpersonapremio;
	}

	public Date getDFechacompra() {
		return this.dFechacompra;
	}

	public void setDFechacompra(Date dFechacompra) {
		this.dFechacompra = dFechacompra;
	}

	public BigDecimal getNPersona() {
		return this.nPersona;
	}

	public void setNPersona(BigDecimal nPersona) {
		this.nPersona = nPersona;
	}

	public BigDecimal getNPremio() {
		return this.nPremio;
	}

	public void setNPremio(BigDecimal nPremio) {
		this.nPremio = nPremio;
	}

	public BigDecimal getNValormillas() {
		return this.nValormillas;
	}

	public void setNValormillas(BigDecimal nValormillas) {
		this.nValormillas = nValormillas;
	}

}