package co.com.ganso.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TB_PAGO database table.
 * 
 */
@Entity
@Table(name="TB_PAGO")
@NamedQuery(name="Pago.findAll", query="SELECT p FROM Pago p")
public class Pago extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDPAGO")
	private long nIdpago;

	@Column(name="N_PAGO_IDOPERACION")
	private java.math.BigDecimal nPagoIdoperacion;

	@Column(name="T_APELLIDOPAGO")
	private String tApellidopago;

	@Column(name="T_CODIGO_PAIS")
	private String tCodigoPais;

	@Column(name="T_CODIGOPOSTAL")
	private String tCodigopostal;

	@Column(name="T_DIRECCION")
	private String tDireccion;

	@Column(name="T_FRANQUICIA")
	private String tFranquicia;

	@Column(name="T_NOMBREPAGO")
	private String tNombrepago;

	@Column(name="T_NUMERODOCUMENTO")
	private String tNumerodocumento;

	@Column(name="T_TELEFONOPAGO")
	private String tTelefonopago;

	public Pago() {
	}

	public long getNIdpago() {
		return this.nIdpago;
	}

	public void setNIdpago(long nIdpago) {
		this.nIdpago = nIdpago;
	}

	public java.math.BigDecimal getNPagoIdoperacion() {
		return this.nPagoIdoperacion;
	}

	public void setNPagoIdoperacion(java.math.BigDecimal nPagoIdoperacion) {
		this.nPagoIdoperacion = nPagoIdoperacion;
	}

	public String getTApellidopago() {
		return this.tApellidopago;
	}

	public void setTApellidopago(String tApellidopago) {
		this.tApellidopago = tApellidopago;
	}

	public String getTCodigoPais() {
		return this.tCodigoPais;
	}

	public void setTCodigoPais(String tCodigoPais) {
		this.tCodigoPais = tCodigoPais;
	}

	public String getTCodigopostal() {
		return this.tCodigopostal;
	}

	public void setTCodigopostal(String tCodigopostal) {
		this.tCodigopostal = tCodigopostal;
	}

	public String getTDireccion() {
		return this.tDireccion;
	}

	public void setTDireccion(String tDireccion) {
		this.tDireccion = tDireccion;
	}

	public String getTFranquicia() {
		return this.tFranquicia;
	}

	public void setTFranquicia(String tFranquicia) {
		this.tFranquicia = tFranquicia;
	}

	public String getTNombrepago() {
		return this.tNombrepago;
	}

	public void setTNombrepago(String tNombrepago) {
		this.tNombrepago = tNombrepago;
	}

	public String getTNumerodocumento() {
		return this.tNumerodocumento;
	}

	public void setTNumerodocumento(String tNumerodocumento) {
		this.tNumerodocumento = tNumerodocumento;
	}

	public String getTTelefonopago() {
		return this.tTelefonopago;
	}

	public void setTTelefonopago(String tTelefonopago) {
		this.tTelefonopago = tTelefonopago;
	}

}