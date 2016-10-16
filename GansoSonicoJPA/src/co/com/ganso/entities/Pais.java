package co.com.ganso.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TB_PAIS database table.
 * 
 */
@Entity
@Table(name="TB_PAIS")
@NamedQuery(name="Pais.findAll", query="SELECT p FROM Pais p")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="T_CODIGO")
	private String tCodigo;

	@Column(name="T_CONTINENTE")
	private String tContinente;

	@Column(name="T_NOMBRE")
	private String tNombre;

	public Pais() {
	}

	public String getTCodigo() {
		return this.tCodigo;
	}

	public void setTCodigo(String tCodigo) {
		this.tCodigo = tCodigo;
	}

	public String getTContinente() {
		return this.tContinente;
	}

	public void setTContinente(String tContinente) {
		this.tContinente = tContinente;
	}

	public String getTNombre() {
		return this.tNombre;
	}

	public void setTNombre(String tNombre) {
		this.tNombre = tNombre;
	}

}