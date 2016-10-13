/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "TB_OPERACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operacion.findAll", query = "SELECT t FROM Operacion t"),
    @NamedQuery(name = "Operacion.findByNIdoperacion", query = "SELECT t FROM Operacion t WHERE t.nIdoperacion = :nIdoperacion"),
    @NamedQuery(name = "Operacion.findByDFechaoperacion", query = "SELECT t FROM Operacion t WHERE t.dFechaoperacion = :dFechaoperacion")})
public class Operacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_IDOPERACION")
    private BigDecimal nIdoperacion;
    @Column(name = "D_FECHAOPERACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFechaoperacion;
    @JoinColumn(name = "N_CLASE", referencedColumnName = "N_IDCLASE")
    @ManyToOne(fetch = FetchType.EAGER)
    private ClaseVuelo nClase;
    @JoinColumn(name = "N_ESTADO", referencedColumnName = "N_IDESTADO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Estado nEstado;
    @JoinColumn(name = "N_PERSONA", referencedColumnName = "N_IDPERSONA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Persona nPersona;
    @JoinColumn(name = "N_VUELO", referencedColumnName = "N_IDVUELO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Vuelo nVuelo;

    public Operacion() {
    }

    public Operacion(BigDecimal nIdoperacion) {
        this.nIdoperacion = nIdoperacion;
    }

    public BigDecimal getNIdoperacion() {
        return nIdoperacion;
    }

    public void setNIdoperacion(BigDecimal nIdoperacion) {
        this.nIdoperacion = nIdoperacion;
    }

    public Date getDFechaoperacion() {
        return dFechaoperacion;
    }

    public void setDFechaoperacion(Date dFechaoperacion) {
        this.dFechaoperacion = dFechaoperacion;
    }

    public ClaseVuelo getNClase() {
        return nClase;
    }

    public void setNClase(ClaseVuelo nClase) {
        this.nClase = nClase;
    }

    public Estado getNEstado() {
        return nEstado;
    }

    public void setNEstado(Estado nEstado) {
        this.nEstado = nEstado;
    }

    public Persona getNPersona() {
        return nPersona;
    }

    public void setNPersona(Persona nPersona) {
        this.nPersona = nPersona;
    }

    public Vuelo getNVuelo() {
        return nVuelo;
    }

    public void setNVuelo(Vuelo nVuelo) {
        this.nVuelo = nVuelo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdoperacion != null ? nIdoperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operacion)) {
            return false;
        }
        Operacion other = (Operacion) object;
        if ((this.nIdoperacion == null && other.nIdoperacion != null) || (this.nIdoperacion != null && !this.nIdoperacion.equals(other.nIdoperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.Operacion[ nIdoperacion=" + nIdoperacion + " ]";
    }
    
}
