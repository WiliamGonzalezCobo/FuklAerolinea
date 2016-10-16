package co.com.ganso.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TB_PLANMILLAS database table.
 * 
 */
@Entity
@Table(name="TB_PLANMILLAS")
@NamedQuery(name="PlanMilla.findAll", query="SELECT p FROM PlanMilla p")
public class PlanMilla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDPLANMILLA")
	private long nIdplanmilla;

	@Column(name="N_MILLASACUMULADAS")
	private BigDecimal nMillasacumuladas;

	@Column(name="N_MILLASREDIMIDAS")
	private BigDecimal nMillasredimidas;

	public PlanMilla() {
	}

	public long getNIdplanmilla() {
		return this.nIdplanmilla;
	}

	public void setNIdplanmilla(long nIdplanmilla) {
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

}