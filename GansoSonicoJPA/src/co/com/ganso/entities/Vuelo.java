package co.com.ganso.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TB_VUELO database table.
 * 
 */
@Entity
@Table(name="TB_VUELO")
@NamedQuery(name="Vuelo.findAll", query="SELECT v FROM Vuelo v")
public class Vuelo extends EntityCore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2304059463081419878L;

	@Id
	@Column(name="N_IDVUELO")
	private BigDecimal nIdvuelo;

	@Temporal(TemporalType.DATE)
	@Column(name="D_FECHAHORASALIDA")
	private Date dFechahorasalida;

	@Column(name="N_CAPACIDAD")
	private BigDecimal nCapacidad;

	@Column(name="N_VALORBASE")
	private BigDecimal nValorbase;

	@Column(name="T_DESTINO")
	private String tDestino;

	@Column(name="T_ORIGEN")
	private String tOrigen;

	public Vuelo() {
	}

	public BigDecimal getNIdvuelo() {
		return this.nIdvuelo;
	}

	public void setNIdvuelo(BigDecimal nIdvuelo) {
		this.nIdvuelo = nIdvuelo;
	}

	public Date getDFechahorasalida() {
		return this.dFechahorasalida;
	}

	public void setDFechahorasalida(Date dFechahorasalida) {
		this.dFechahorasalida = dFechahorasalida;
	}

	public BigDecimal getNCapacidad() {
		return this.nCapacidad;
	}

	public void setNCapacidad(BigDecimal nCapacidad) {
		this.nCapacidad = nCapacidad;
	}

	public BigDecimal getNValorbase() {
		return this.nValorbase;
	}

	public void setNValorbase(BigDecimal nValorbase) {
		this.nValorbase = nValorbase;
	}

	public String getTDestino() {
		return this.tDestino;
	}

	public void setTDestino(String tDestino) {
		this.tDestino = tDestino;
	}

	public String getTOrigen() {
		return this.tOrigen;
	}

	public void setTOrigen(String tOrigen) {
		this.tOrigen = tOrigen;
	}

}