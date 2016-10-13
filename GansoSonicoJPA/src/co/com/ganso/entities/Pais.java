/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.entities;

import java.io.Serializable;
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
@Table(name = "TB_PAIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT t FROM Pais t"),
    @NamedQuery(name = "Pais.findByTCodigo", query = "SELECT t FROM Pais t WHERE t.tCodigo = :tCodigo"),
    @NamedQuery(name = "Pais.findByTNombre", query = "SELECT t FROM Pais t WHERE t.tNombre = :tNombre"),
    @NamedQuery(name = "Pais.findByTContinente", query = "SELECT t FROM Pais t WHERE t.tContinente = :tContinente")})
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "T_CODIGO")
    private String tCodigo;
    @Column(name = "T_NOMBRE")
    private String tNombre;
    @Column(name = "T_CONTINENTE")
    private String tContinente;
    @OneToMany(mappedBy = "tPais", fetch = FetchType.EAGER)
    private List<Aeropuerto> tbAeropuertoList;

    public Pais() {
    }

    public Pais(String tCodigo) {
        this.tCodigo = tCodigo;
    }

    public String getTCodigo() {
        return tCodigo;
    }

    public void setTCodigo(String tCodigo) {
        this.tCodigo = tCodigo;
    }

    public String getTNombre() {
        return tNombre;
    }

    public void setTNombre(String tNombre) {
        this.tNombre = tNombre;
    }

    public String getTContinente() {
        return tContinente;
    }

    public void setTContinente(String tContinente) {
        this.tContinente = tContinente;
    }

    @XmlTransient
    public List<Aeropuerto> getTbAeropuertoList() {
        return tbAeropuertoList;
    }

    public void setTbAeropuertoList(List<Aeropuerto> tbAeropuertoList) {
        this.tbAeropuertoList = tbAeropuertoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tCodigo != null ? tCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.tCodigo == null && other.tCodigo != null) || (this.tCodigo != null && !this.tCodigo.equals(other.tCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.Pais[ tCodigo=" + tCodigo + " ]";
    }
    
}
