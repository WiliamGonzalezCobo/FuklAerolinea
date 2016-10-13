/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "TB_VUELO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vuelo.findAll", query = "SELECT t FROM Vuelo t"),
    @NamedQuery(name = "Vuelo.findByNIdvuelo", query = "SELECT t FROM Vuelo t WHERE t.nIdvuelo = :nIdvuelo"),
    @NamedQuery(name = "Vuelo.findByDFechahorasalida", query = "SELECT t FROM Vuelo t WHERE t.dFechahorasalida = :dFechahorasalida"),
    @NamedQuery(name = "Vuelo.findByNCapacidad", query = "SELECT t FROM Vuelo t WHERE t.nCapacidad = :nCapacidad"),
    @NamedQuery(name = "Vuelo.findByNValorbase", query = "SELECT t FROM Vuelo t WHERE t.nValorbase = :nValorbase")})
public class Vuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_IDVUELO")
    private BigDecimal nIdvuelo;
    @Column(name = "D_FECHAHORASALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFechahorasalida;
    @Column(name = "N_CAPACIDAD")
    private BigInteger nCapacidad;
    @Column(name = "N_VALORBASE")
    private BigInteger nValorbase;
    @JoinColumn(name = "T_ORIGEN", referencedColumnName = "T_CODIGO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Aeropuerto tOrigen;
    @JoinColumn(name = "T_DESTINO", referencedColumnName = "T_CODIGO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Aeropuerto tDestino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nVuelo", fetch = FetchType.EAGER)
    private List<Operacion> tbOperacionList;

    public Vuelo() {
    }

    public Vuelo(BigDecimal nIdvuelo) {
        this.nIdvuelo = nIdvuelo;
    }

    public BigDecimal getNIdvuelo() {
        return nIdvuelo;
    }

    public void setNIdvuelo(BigDecimal nIdvuelo) {
        this.nIdvuelo = nIdvuelo;
    }

    public Date getDFechahorasalida() {
        return dFechahorasalida;
    }

    public void setDFechahorasalida(Date dFechahorasalida) {
        this.dFechahorasalida = dFechahorasalida;
    }

    public BigInteger getNCapacidad() {
        return nCapacidad;
    }

    public void setNCapacidad(BigInteger nCapacidad) {
        this.nCapacidad = nCapacidad;
    }

    public BigInteger getNValorbase() {
        return nValorbase;
    }

    public void setNValorbase(BigInteger nValorbase) {
        this.nValorbase = nValorbase;
    }

    public Aeropuerto getTOrigen() {
        return tOrigen;
    }

    public void setTOrigen(Aeropuerto tOrigen) {
        this.tOrigen = tOrigen;
    }

    public Aeropuerto getTDestino() {
        return tDestino;
    }

    public void setTDestino(Aeropuerto tDestino) {
        this.tDestino = tDestino;
    }

    @XmlTransient
    public List<Operacion> getTbOperacionList() {
        return tbOperacionList;
    }

    public void setTbOperacionList(List<Operacion> tbOperacionList) {
        this.tbOperacionList = tbOperacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdvuelo != null ? nIdvuelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vuelo)) {
            return false;
        }
        Vuelo other = (Vuelo) object;
        if ((this.nIdvuelo == null && other.nIdvuelo != null) || (this.nIdvuelo != null && !this.nIdvuelo.equals(other.nIdvuelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.Vuelo[ nIdvuelo=" + nIdvuelo + " ]";
    }
    
}
