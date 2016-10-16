package co.com.ganso.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TB_CLASEVUELO database table.
 * 
 */
@Entity
@Table(name="TB_CLASEVUELO")
@NamedQuery(name="ClaseVuelo.findAll", query="SELECT c FROM ClaseVuelo c")
public class ClaseVuelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDCLASE")
	private long nIdclase;

	@Column(name="T_NOMBRE")
	private String tNombre;

	public ClaseVuelo() {
	}

	public long getNIdclase() {
		return this.nIdclase;
	}

	public void setNIdclase(long nIdclase) {
		this.nIdclase = nIdclase;
	}

	public String getTNombre() {
		return this.tNombre;
	}

	public void setTNombre(String tNombre) {
		this.tNombre = tNombre;
	}

}