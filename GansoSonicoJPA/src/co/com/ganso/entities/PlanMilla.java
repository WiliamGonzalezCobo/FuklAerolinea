package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TB_PLANMILLAS database table.
 * 
 */
@Entity
@Table(name="TB_PLANMILLAS")
@NamedQuery(name="PlanMilla.findAll", query="SELECT p FROM PlanMilla p")
public class PlanMilla extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDPLANMILLA")
	private BigDecimal nIdplanmilla;

	@Column(name="N_MILLASACUMULADAS")
	private BigDecimal nMillasacumuladas;

	@Column(name="N_MILLASREDIMIDAS")
	private BigDecimal nMillasredimidas;
	
	@Column(name="N_IDPERSONA")
	private BigDecimal nIdPersona;

	public PlanMilla() {
	}

	public BigDecimal getNIdplanmilla() {
		return this.nIdplanmilla;
	}

	public void setNIdplanmilla(BigDecimal nIdplanmilla) {
		this.nIdplanmilla = nIdplanmilla;
	}

	public BigDecimal getNMillasacumuladas() {
		return this.nMillasacumuladas;
	}

	public void setNMillasacumuladas(BigDecimal nMillasacumuladas) {
		this.nMillasacumuladas = nMillasacumuladas;
	}

	public BigDecimal getNMillasredimidas() {
		return this.nMillasredimidas;
	}

	public void setNMillasredimidas(BigDecimal nMillasredimidas) {
		this.nMillasredimidas = nMillasredimidas;
	}

	public BigDecimal getnIdPersona() {
		return nIdPersona;
	}

	public void setnIdPersona(BigDecimal nIdPersona) {
		this.nIdPersona = nIdPersona;
	}

}