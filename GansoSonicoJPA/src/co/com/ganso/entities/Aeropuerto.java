package co.com.ganso.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * The persistent class for the TB_AEROPUERTO database table.
 * 
 */
@Entity
@Table(name="TB_AEROPUERTO")
@NamedQuery(name="Aeropuerto.findAll", query="SELECT a FROM Aeropuerto a")
public class Aeropuerto extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="T_CODIGO")
	private String tCodigo;

	@Column(name="T_CIUDAD")
	private String tCiudad;

	@Column(name="T_NOMBRE")
	private String tNombre;

	@Column(name="T_PAIS")
	private String tPais;

	@Column(name="T_TIPO")
	private String tTipo;
	
	@PrimaryKeyJoinColumn(name = "T_PAIS", referencedColumnName = "T_CODIGO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Pais pais;

	public Aeropuerto() {
	}

	public String getTCodigo() {
		return this.tCodigo;
	}

	public void setTCodigo(String tCodigo) {
		this.tCodigo = tCodigo;
	}

	public String getTCiudad() {
		return this.tCiudad;
	}

	public void setTCiudad(String tCiudad) {
		this.tCiudad = tCiudad;
	}

	public String getTNombre() {
		return this.tNombre;
	}

	public void setTNombre(String tNombre) {
		this.tNombre = tNombre;
	}

	public String getTPais() {
		return this.tPais;
	}

	public void setTPais(String tPais) {
		this.tPais = tPais;
	}

	public String getTTipo() {
		return this.tTipo;
	}

	public void setTTipo(String tTipo) {
		this.tTipo = tTipo;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}