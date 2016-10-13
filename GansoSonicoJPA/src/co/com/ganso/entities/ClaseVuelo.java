/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "TB_CLASEVUELO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClaseVuelo.findAll", query = "SELECT t FROM ClaseVuelo t"),
    @NamedQuery(name = "ClaseVuelo.findByNIdclase", query = "SELECT t FROM ClaseVuelo t WHERE t.nIdclase = :nIdclase"),
    @NamedQuery(name = "ClaseVuelo.findByTNombre", query = "SELECT t FROM ClaseVuelo t WHERE t.tNombre = :tNombre")})
public class ClaseVuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_IDCLASE")
    private BigDecimal nIdclase;
    @Column(name = "T_NOMBRE")
    private String tNombre;
    @OneToMany(mappedBy = "nClase", fetch = FetchType.EAGER)
    private List<FactorPrecio> tbFactorprecioList;
    @OneToMany(mappedBy = "nClase", fetch = FetchType.EAGER)
    private List<Operacion> tbOperacionList;

    public ClaseVuelo() {
    }

    public ClaseVuelo(BigDecimal nIdclase) {
        this.nIdclase = nIdclase;
    }

    public BigDecimal getNIdclase() {
        return nIdclase;
    }

    public void setNIdclase(BigDecimal nIdclase) {
        this.nIdclase = nIdclase;
    }

    public String getTNombre() {
        return tNombre;
    }

    public void setTNombre(String tNombre) {
        this.tNombre = tNombre;
    }

    @XmlTransient
    public List<FactorPrecio> getTbFactorprecioList() {
        return tbFactorprecioList;
    }

    public void setTbFactorprecioList(List<FactorPrecio> tbFactorprecioList) {
        this.tbFactorprecioList = tbFactorprecioList;
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
        hash += (nIdclase != null ? nIdclase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClaseVuelo)) {
            return false;
        }
        ClaseVuelo other = (ClaseVuelo) object;
        if ((this.nIdclase == null && other.nIdclase != null) || (this.nIdclase != null && !this.nIdclase.equals(other.nIdclase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.ClaseVuelo[ nIdclase=" + nIdclase + " ]";
    }
    
}
