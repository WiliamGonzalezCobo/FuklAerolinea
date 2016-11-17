package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import co.com.ganso.services.bussinesslogic.AnotacionParametro;


/**
 * The persistent class for the TB_VUELOITINERARIO database table.
 * 
 */
@Entity
@Table(name="TB_VUELOITINERARIO")
@NamedQueries({
@NamedQuery(name="VueloItinerario.findAll", query="SELECT t FROM VueloItinerario t"),
@NamedQuery(name="VueloItinerario.findEscalas", query="SELECT t FROM VueloItinerario t WHERE t.nItinerario = :nItinerario ORDER BY t.nOrden")})
public class VueloItinerario extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDVUELOITINERARIO")
	private BigDecimal nIdvueloitinerario;

	@Column(name="N_ITINERARIO")
	private BigDecimal nItinerario;

	@Column(name="N_VUELO")
	private BigDecimal nVuelo;
	
	@Column(name="N_ORDEN")
	private BigDecimal nOrden;
	
	@PrimaryKeyJoinColumn(name = "N_VUELO", referencedColumnName = "N_IDVUELO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Vuelo vuelo;

	public VueloItinerario() {
	}
	
	public BigDecimal getNIdvueloitinerario() {
		return this.nIdvueloitinerario;
	}

	public void setNIdvueloitinerario(BigDecimal nIdvueloitinerario) {
		this.nIdvueloitinerario = nIdvueloitinerario;
	}
	
	@AnotacionParametro
	public BigDecimal getNItinerario() {
		return this.nItinerario;
	}

	public void setNItinerario(BigDecimal nItinerario) {
		this.nItinerario = nItinerario;
	}

	public BigDecimal getNOrden() {
		return this.nOrden;
	}

	public void setNOrden(BigDecimal nOrden) {
		this.nOrden = nOrden;
	}

	public BigDecimal getNVuelo() {
		return this.nVuelo;
	}

	public void setNVuelo(BigDecimal nVuelo) {
		this.nVuelo = nVuelo;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

}