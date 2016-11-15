package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the TB_ITINERARIO database table.
 * 
 */
@Entity
@Table(name="TB_ITINERARIO")
@NamedQuery(name="Itinerario.findAll", query="SELECT i FROM Itinerario i ORDER BY i.nIditinerario DESC")
public class Itinerario extends EntityCore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="N_IDITINERARIO")
	private BigDecimal nIditinerario;

	@Column(name="T_DESTINO")
	private String tDestino;

	@Column(name="T_ORIGEN")
	private String tOrigen;
	
	@Column(name="D_FECHA")
	private String dFecha;
	
	@PrimaryKeyJoinColumn(name = "T_ORIGEN", referencedColumnName = "T_CODIGO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Aeropuerto origen;
	
	@PrimaryKeyJoinColumn(name = "T_DESTINO", referencedColumnName = "T_CODIGO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Aeropuerto destino;
	
	@Transient
	private String dFechaRegreso;
	
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

	public Itinerario() {
	}

	public BigDecimal getNIditinerario() {
		return this.nIditinerario;
	}

	public void setNIditinerario(BigDecimal nIditinerario) {
		this.nIditinerario = nIditinerario;
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

	public String getDFecha() {
		return dFecha;
	}

	public void setDFecha(String dFecha) {
		this.dFecha = dFecha;
	}

	public String getdFechaRegreso() {
		return dFechaRegreso;
	}

	public void setdFechaRegreso(String dFechaRegreso) {
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

}