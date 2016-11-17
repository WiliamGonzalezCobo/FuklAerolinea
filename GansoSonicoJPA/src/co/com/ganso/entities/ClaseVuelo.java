package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TB_CLASEVUELO database table.
 * 
 */
@Entity
@Table(name="TB_CLASEVUELO")
@NamedQuery(name="ClaseVuelo.findAll", query="SELECT c FROM ClaseVuelo c")
public class ClaseVuelo extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDCLASE")
	private BigDecimal nIdclase;

	@Column(name="T_NOMBRE")
	private String tNombre;

	public ClaseVuelo() {
	}

	public BigDecimal getNIdclase() {
		return this.nIdclase;
	}

	public void setNIdclase(BigDecimal nIdclase) {
		this.nIdclase = nIdclase;
	}

	public String getTNombre() {
		return this.tNombre;
	}

	public void setTNombre(String tNombre) {
		this.tNombre = tNombre;
	}

}