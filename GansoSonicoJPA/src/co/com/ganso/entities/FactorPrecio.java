/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "TB_FACTORPRECIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactorPrecio.findAll", query = "SELECT t FROM FactorPrecio t"),
    @NamedQuery(name = "FactorPrecio.findByNIdfactor", query = "SELECT t FROM FactorPrecio t WHERE t.nIdfactor = :nIdfactor"),
    @NamedQuery(name = "FactorPrecio.findByTDescripcion", query = "SELECT t FROM FactorPrecio t WHERE t.tDescripcion = :tDescripcion"),
    @NamedQuery(name = "FactorPrecio.findByNFactor", query = "SELECT t FROM FactorPrecio t WHERE t.nFactor = :nFactor")})
public class FactorPrecio implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_IDFACTOR")
    private BigDecimal nIdfactor;
    @Column(name = "T_DESCRIPCION")
    private String tDescripcion;
    @Column(name = "N_FACTOR")
    private BigInteger nFactor;
    @JoinColumn(name = "N_CLASE", referencedColumnName = "N_IDCLASE")
    @ManyToOne(fetch = FetchType.EAGER)
    private ClaseVuelo nClase;

    public FactorPrecio() {
    }

    public FactorPrecio(BigDecimal nIdfactor) {
        this.nIdfactor = nIdfactor;
    }

    public BigDecimal getNIdfactor() {
        return nIdfactor;
    }

    public void setNIdfactor(BigDecimal nIdfactor) {
        this.nIdfactor = nIdfactor;
    }

    public String getTDescripcion() {
        return tDescripcion;
    }

    public void setTDescripcion(String tDescripcion) {
        this.tDescripcion = tDescripcion;
    }

    public BigInteger getNFactor() {
        return nFactor;
    }

    public void setNFactor(BigInteger nFactor) {
        this.nFactor = nFactor;
    }

    public ClaseVuelo getNClase() {
        return nClase;
    }

    public void setNClase(ClaseVuelo nClase) {
        this.nClase = nClase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdfactor != null ? nIdfactor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactorPrecio)) {
            return false;
        }
        FactorPrecio other = (FactorPrecio) object;
        if ((this.nIdfactor == null && other.nIdfactor != null) || (this.nIdfactor != null && !this.nIdfactor.equals(other.nIdfactor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.FactorPrecio[ nIdfactor=" + nIdfactor + " ]";
    }
    
}
