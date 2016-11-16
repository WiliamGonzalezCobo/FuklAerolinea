package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TB_PERSONA database table.
 * 
 */
@Entity
@Table(name="TB_PERSONA")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDPERSONA")
	private BigDecimal nIdpersona;

	@Temporal(TemporalType.DATE)
	@Column(name="D_FECHANACIMIENTO")
	private Date dFechanacimiento;

	@Column(name="N_CATEGORIA")
	private Integer nCategoria;

	@Column(name="T_APELLIDOS")
	private String tApellidos;

	@Column(name="T_DIRECCION")
	private String tDireccion;

	@Column(name="T_NOMBRES")
	private String tNombres;

	@Column(name="T_PASAPORTE")
	private String tPasaporte;

	@Column(name="T_TELEFONO")
	private String tTelefono;

	public Persona() {
	}

	public BigDecimal getNIdpersona() {
		return this.nIdpersona;
	}

	public void setNIdpersona(BigDecimal nIdpersona) {
		this.nIdpersona = nIdpersona;
	}

	public Date getDFechanacimiento() {
		return this.dFechanacimiento;
	}

	public void setDFechanacimiento(Date dFechanacimiento) {
		this.dFechanacimiento = dFechanacimiento;
	}

	public Integer getNCategoria() {
		return this.nCategoria;
	}

	public void setNCategoria(Integer nCategoria) {
		this.nCategoria = nCategoria;
	}

	public String getTApellidos() {
		return this.tApellidos;
	}

	public void setTApellidos(String tApellidos) {
		this.tApellidos = tApellidos;
	}

	public String getTDireccion() {
		return this.tDireccion;
	}

	public void setTDireccion(String tDireccion) {
		this.tDireccion = tDireccion;
	}

	public String getTNombres() {
		return this.tNombres;
	}

	public void setTNombres(String tNombres) {
		this.tNombres = tNombres;
	}

	public String getTPasaporte() {
		return this.tPasaporte;
	}

	public void setTPasaporte(String tPasaporte) {
		this.tPasaporte = tPasaporte;
	}

	public String getTTelefono() {
		return this.tTelefono;
	}

	public void setTTelefono(String tTelefono) {
		this.tTelefono = tTelefono;
	}

}