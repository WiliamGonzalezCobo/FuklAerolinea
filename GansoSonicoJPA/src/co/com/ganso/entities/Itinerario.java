package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import co.com.ganso.services.bussinesslogic.AnotacionParametro;


/**
 * The persistent class for the TB_ITINERARIO database table.
 * 
 */
@Entity
@Table(name="TB_ITINERARIO")
@NamedQueries({
	@NamedQuery(name="Itinerario.findAll", query="SELECT i FROM Itinerario i ORDER BY i.nIditinerario DESC"),
	@NamedQuery(name="Itinerario.findItinerarios", query="SELECT i FROM Itinerario i WHERE i.tOrigen = :tOrigen AND i.tDestino = :tDestino and i.dFecha = :dFecha ORDER BY i.nIditinerario DESC")})
public class Itinerario extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDITINERARIO")
	private BigDecimal nIditinerario;

	@Column(name="T_DESTINO")
	private String tDestino;

	@Column(name="T_ORIGEN")
	private String tOrigen;
	
	@Temporal(TemporalType.DATE)
	@Column(name="D_FECHA")
	private Date dFecha;
	
	@PrimaryKeyJoinColumn(name = "T_ORIGEN", referencedColumnName = "T_CODIGO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Aeropuerto origen;
	
	@PrimaryKeyJoinColumn(name = "T_DESTINO", referencedColumnName = "T_CODIGO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Aeropuerto destino;
	
	@Transient
	private Date dFechaRegreso;
	
	@Transient
	private Integer cantidadAdultos;
	
	@Transient
	private Integer cantidadNinos;
	
	@Transient
	private Integer cantidadBebes;
	
	@Transient
	private BigDecimal clase;
	
	@Transient
	private String claseDesc;
	
	@Transient
	private String flexible;
	
	@Transient
    private List<VueloItinerario> vuelos;

	public Itinerario() {
	}

	public BigDecimal getNIditinerario() {
		return this.nIditinerario;
	}

	public void setNIditinerario(BigDecimal nIditinerario) {
		this.nIditinerario = nIditinerario;
	}
	
	@AnotacionParametro
	public String getTDestino() {
		return this.tDestino;
	}

	public void setTDestino(String tDestino) {
		this.tDestino = tDestino;
	}

	@AnotacionParametro
	public String getTOrigen() {
		return this.tOrigen;
	}

	public void setTOrigen(String tOrigen) {
		this.tOrigen = tOrigen;
	}

	public Aeropuerto getOrigen() {
		return origen;
	}

	public void setOrigen(Aeropuerto origen) {
		this.origen = origen;
	}

	public Aeropuerto getDestino() {
		return destino;
	}

	public void setDestino(Aeropuerto destino) {
		this.destino = destino;
	}

	@AnotacionParametro
	public Date getDFecha() {
		return dFecha;
	}

	public void setDFecha(Date dFecha) {
		this.dFecha = dFecha;
	}

	public Date getdFechaRegreso() {
		return dFechaRegreso;
	}

	public void setdFechaRegreso(Date dFechaRegreso) {
		this.dFechaRegreso = dFechaRegreso;
	}

	public Integer getCantidadAdultos() {
		return cantidadAdultos;
	}

	public void setCantidadAdultos(Integer cantidadAdultos) {
		this.cantidadAdultos = cantidadAdultos;
	}

	public Integer getCantidadNinos() {
		return cantidadNinos;
	}

	public void setCantidadNinos(Integer cantidadNinos) {
		this.cantidadNinos = cantidadNinos;
	}

	public Integer getCantidadBebes() {
		return cantidadBebes;
	}

	public void setCantidadBebes(Integer cantidadBebes) {
		this.cantidadBebes = cantidadBebes;
	}

	public BigDecimal getClase() {
		return clase;
	}

	public void setClase(BigDecimal clase) {
		this.clase = clase;
	}

	public String getFlexible() {
		return flexible;
	}

	public void setFlexible(String flexible) {
		this.flexible = flexible;
	}

	public String getClaseDesc() {
		return claseDesc;
	}

	public void setClaseDesc(String claseDesc) {
		this.claseDesc = claseDesc;
	}

	public List<VueloItinerario> getVuelos() {
		return vuelos;
	}

	public void setVuelos(List<VueloItinerario> vuelos) {
		this.vuelos = vuelos;
	}

}