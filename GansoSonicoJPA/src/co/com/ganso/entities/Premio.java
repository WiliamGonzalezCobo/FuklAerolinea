/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "TB_PREMIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Premio.findAll", query = "SELECT t FROM Premio t"),
    @NamedQuery(name = "Premio.findByNIdpremio", query = "SELECT t FROM Premio t WHERE t.nIdpremio = :nIdpremio"),
    @NamedQuery(name = "Premio.findByTNombrepremio", query = "SELECT t FROM Premio t WHERE t.tNombrepremio = :tNombrepremio"),
    @NamedQuery(name = "Premio.findByTDescripcion", query = "SELECT t FROM Premio t WHERE t.tDescripcion = :tDescripcion"),
    @NamedQuery(name = "Premio.findByNValormillas", query = "SELECT t FROM Premio t WHERE t.nValormillas = :nValormillas"),
    @NamedQuery(name = "Premio.findByTRutaimagen", query = "SELECT t FROM Premio t WHERE t.tRutaimagen = :tRutaimagen"),
    @NamedQuery(name = "Premio.findByTActivo", query = "SELECT t FROM Premio t WHERE t.tActivo = :tActivo")})
public class Premio implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_IDPREMIO")
    private BigDecimal nIdpremio;
    @Column(name = "T_NOMBREPREMIO")
    private String tNombrepremio;
    @Column(name = "T_DESCRIPCION")
    private String tDescripcion;
    @Column(name = "N_VALORMILLAS")
    private BigInteger nValormillas;
    @Column(name = "T_RUTAIMAGEN")
    private String tRutaimagen;
    @Column(name = "T_ACTIVO")
    private Character tActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nPremio", fetch = FetchType.EAGER)
    private List<PersonaPremio> tbPersonapremioList;

    public Premio() {
    }

    public Premio(BigDecimal nIdpremio) {
        this.nIdpremio = nIdpremio;
    }

    public BigDecimal getNIdpremio() {
        return nIdpremio;
    }

    public void setNIdpremio(BigDecimal nIdpremio) {
        this.nIdpremio = nIdpremio;
    }

    public String getTNombrepremio() {
        return tNombrepremio;
    }

    public void setTNombrepremio(String tNombrepremio) {
        this.tNombrepremio = tNombrepremio;
    }

    public String getTDescripcion() {
        return tDescripcion;
    }

    public void setTDescripcion(String tDescripcion) {
        this.tDescripcion = tDescripcion;
    }

    public BigInteger getNValormillas() {
        return nValormillas;
    }

    public void setNValormillas(BigInteger nValormillas) {
        this.nValormillas = nValormillas;
    }

    public String getTRutaimagen() {
        return tRutaimagen;
    }

    public void setTRutaimagen(String tRutaimagen) {
        this.tRutaimagen = tRutaimagen;
    }

    public Character getTActivo() {
        return tActivo;
    }

    public void setTActivo(Character tActivo) {
        this.tActivo = tActivo;
    }

    @XmlTransient
    public List<PersonaPremio> getTbPersonapremioList() {
        return tbPersonapremioList;
    }

    public void setTbPersonapremioList(List<PersonaPremio> tbPersonapremioList) {
        this.tbPersonapremioList = tbPersonapremioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdpremio != null ? nIdpremio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Premio)) {
            return false;
        }
        Premio other = (Premio) object;
        if ((this.nIdpremio == null && other.nIdpremio != null) || (this.nIdpremio != null && !this.nIdpremio.equals(other.nIdpremio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.Premio[ nIdpremio=" + nIdpremio + " ]";
    }
    
}
