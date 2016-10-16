package co.com.ganso.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TB_CATEGORIA database table.
 * 
 */
@Entity
@Table(name="TB_CATEGORIA")
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDCATEGORIA")
	private long nIdcategoria;

	@Column(name="T_DESCRIPCION")
	private String tDescripcion;

	@Column(name="T_NOMBRE")
	private String tNombre;

	public Categoria() {
	}

	public long getNIdcategoria() {
		return this.nIdcategoria;
	}

	public void setNIdcategoria(long nIdcategoria) {
		this.nIdcategoria = nIdcategoria;
	}

	public String getTDescripcion() {
		return this.tDescripcion;
	}

	public void setTDescripcion(String tDescripcion) {
		this.tDescripcion = tDescripcion;
	}

	public String getTNombre() {
		return this.tNombre;
	}

	public void setTNombre(String tNombre) {
		this.tNombre = tNombre;
	}

}