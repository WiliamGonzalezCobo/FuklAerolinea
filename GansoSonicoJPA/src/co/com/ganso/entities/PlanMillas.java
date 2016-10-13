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
@Table(name = "TB_PLANMILLAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanMillas.findAll", query = "SELECT t FROM PlanMillas t"),
    @NamedQuery(name = "PlanMillas.findByNIdplanmilla", query = "SELECT t FROM PlanMillas t WHERE t.nIdplanmilla = :nIdplanmilla"),
    @NamedQuery(name = "PlanMillas.findByNMillasacumuladas", query = "SELECT t FROM PlanMillas t WHERE t.nMillasacumuladas = :nMillasacumuladas"),
    @NamedQuery(name = "PlanMillas.findByNMillasredimidas", query = "SELECT t FROM PlanMillas t WHERE t.nMillasredimidas = :nMillasredimidas")})
public class PlanMillas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_IDPLANMILLA")
    private BigDecimal nIdplanmilla;
    @Column(name = "N_MILLASACUMULADAS")
    private BigInteger nMillasacumuladas;
    @Column(name = "N_MILLASREDIMIDAS")
    private BigInteger nMillasredimidas;
    @OneToMany(mappedBy = "nPlanmilla", fetch = FetchType.EAGER)
    private List<Persona> tbPersonaList;

    public PlanMillas() {
    }

    public PlanMillas(BigDecimal nIdplanmilla) {
        this.nIdplanmilla = nIdplanmilla;
    }

    public BigDecimal getNIdplanmilla() {
        return nIdplanmilla;
    }

    public void setNIdplanmilla(BigDecimal nIdplanmilla) {
        this.nIdplanmilla = nIdplanmilla;
    }

    public BigInteger getNMillasacumuladas() {
        return nMillasacumuladas;
    }

    public void setNMillasacumuladas(BigInteger nMillasacumuladas) {
        this.nMillasacumuladas = nMillasacumuladas;
    }

    public BigInteger getNMillasredimidas() {
        return nMillasredimidas;
    }

    public void setNMillasredimidas(BigInteger nMillasredimidas) {
        this.nMillasredimidas = nMillasredimidas;
    }

    @XmlTransient
    public List<Persona> getTbPersonaList() {
        return tbPersonaList;
    }

    public void setTbPersonaList(List<Persona> tbPersonaList) {
        this.tbPersonaList = tbPersonaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdplanmilla != null ? nIdplanmilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanMillas)) {
            return false;
        }
        PlanMillas other = (PlanMillas) object;
        if ((this.nIdplanmilla == null && other.nIdplanmilla != null) || (this.nIdplanmilla != null && !this.nIdplanmilla.equals(other.nIdplanmilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.PlanMillas[ nIdplanmilla=" + nIdplanmilla + " ]";
    }
    
}
