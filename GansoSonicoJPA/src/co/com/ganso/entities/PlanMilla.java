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
 * The persistent class for the TB_PLANMILLAS database table.
 * 
 */
@Entity
@Table(name="TB_PLANMILLAS")
@NamedQueries({
@NamedQuery(name="PlanMilla.findIdPersona", query=PlanMilla.FIND_MILLAS_PERSONA),
@NamedQuery(name="PlanMilla.findAll", query="SELECT p FROM PlanMilla p")})
public class PlanMilla extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDPLANMILLA")
	private Integer nIdplanmilla;

	@Column(name="N_MILLASACUMULADAS")
	private Integer nMillasacumuladas;

	@Column(name="N_MILLASREDIMIDAS")
	private Integer nMillasredimidas;
	
	@Column(name="N_IDPERSONA")
	private Integer nIdPersona;

	public PlanMilla() {
	}

	public Integer getNIdplanmilla() {
		return this.nIdplanmilla;
	}

	public void setNIdplanmilla(Integer nIdplanmilla) {
		this.nIdplanmilla = nIdplanmilla;
	}

	public Integer getNMillasacumuladas() {
		return this.nMillasacumuladas;
	}

	public void setNMillasacumuladas(Integer nMillasacumuladas) {
		this.nMillasacumuladas = nMillasacumuladas;
	}

	public Integer getNMillasredimidas() {
		return this.nMillasredimidas;
	}

	public void setNMillasredimidas(Integer nMillasredimidas) {
		this.nMillasredimidas = nMillasredimidas;
	}
	
	@AnotacionParametro
	public Integer getnIdPersona() {
		return nIdPersona;
	}

	public void setnIdPersona(Integer nIdPersona) {
		this.nIdPersona = nIdPersona;
	}
	protected static final String FIND_MILLAS_PERSONA = "SELECT p "
		    + "FROM PlanMilla p "
		    + "WHERE p.nIdPersona = :nIdPersona";
}