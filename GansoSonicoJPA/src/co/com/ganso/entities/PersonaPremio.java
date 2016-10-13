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
@Table(name = "TB_PERSONAPREMIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaPremio.findAll", query = "SELECT t FROM PersonaPremio t"),
    @NamedQuery(name = "PersonaPremio.findByNIdpersonapremio", query = "SELECT t FROM PersonaPremio t WHERE t.nIdpersonapremio = :nIdpersonapremio"),
    @NamedQuery(name = "PersonaPremio.findByDFechacompra", query = "SELECT t FROM PersonaPremio t WHERE t.dFechacompra = :dFechacompra"),
    @NamedQuery(name = "PersonaPremio.findByNValormillas", query = "SELECT t FROM PersonaPremio t WHERE t.nValormillas = :nValormillas")})
public class PersonaPremio implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_IDPERSONAPREMIO")
    private BigDecimal nIdpersonapremio;
    @Column(name = "D_FECHACOMPRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFechacompra;
    @Column(name = "N_VALORMILLAS")
    private BigInteger nValormillas;
    @JoinColumn(name = "N_PERSONA", referencedColumnName = "N_IDPERSONA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Persona nPersona;
    @JoinColumn(name = "N_PREMIO", referencedColumnName = "N_IDPREMIO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Premio nPremio;

    public PersonaPremio() {
    }

    public PersonaPremio(BigDecimal nIdpersonapremio) {
        this.nIdpersonapremio = nIdpersonapremio;
    }

    public BigDecimal getNIdpersonapremio() {
        return nIdpersonapremio;
    }

    public void setNIdpersonapremio(BigDecimal nIdpersonapremio) {
        this.nIdpersonapremio = nIdpersonapremio;
    }

    public Date getDFechacompra() {
        return dFechacompra;
    }

    public void setDFechacompra(Date dFechacompra) {
        this.dFechacompra = dFechacompra;
    }

    public BigInteger getNValormillas() {
        return nValormillas;
    }

    public void setNValormillas(BigInteger nValormillas) {
        this.nValormillas = nValormillas;
    }

    public Persona getNPersona() {
        return nPersona;
    }

    public void setNPersona(Persona nPersona) {
        this.nPersona = nPersona;
    }

    public Premio getNPremio() {
        return nPremio;
    }

    public void setNPremio(Premio nPremio) {
        this.nPremio = nPremio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdpersonapremio != null ? nIdpersonapremio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaPremio)) {
            return false;
        }
        PersonaPremio other = (PersonaPremio) object;
        if ((this.nIdpersonapremio == null && other.nIdpersonapremio != null) || (this.nIdpersonapremio != null && !this.nIdpersonapremio.equals(other.nIdpersonapremio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.PersonaPremio[ nIdpersonapremio=" + nIdpersonapremio + " ]";
    }
    
}
