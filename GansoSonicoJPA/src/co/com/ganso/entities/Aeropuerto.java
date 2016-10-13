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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TB_AEROPUERTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aeropuerto.findAll", query = "SELECT t FROM Aeropuerto t"),
    @NamedQuery(name = "Aeropuerto.findByTCodigo", query = "SELECT t FROM Aeropuerto t WHERE t.tCodigo = :tCodigo"),
    @NamedQuery(name = "Aeropuerto.findByTTipo", query = "SELECT t FROM Aeropuerto t WHERE t.tTipo = :tTipo"),
    @NamedQuery(name = "Aeropuerto.findByTNombre", query = "SELECT t FROM Aeropuerto t WHERE t.tNombre = :tNombre"),
    @NamedQuery(name = "Aeropuerto.findByTCiudad", query = "SELECT t FROM Aeropuerto t WHERE t.tCiudad = :tCiudad")})
public class Aeropuerto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "T_CODIGO")
    private String tCodigo;
    @Column(name = "T_TIPO")
    private String tTipo;
    @Column(name = "T_NOMBRE")
    private String tNombre;
    @Column(name = "T_CIUDAD")
    private String tCiudad;
    @JoinColumn(name = "T_PAIS", referencedColumnName = "T_CODIGO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Pais tPais;
    @OneToMany(mappedBy = "tOrigen", fetch = FetchType.EAGER)
    private List<Vuelo> tbVueloList;
    @OneToMany(mappedBy = "tDestino", fetch = FetchType.EAGER)
    private List<Vuelo> tbVueloList1;

    public Aeropuerto() {
    }

    public Aeropuerto(String tCodigo) {
        this.tCodigo = tCodigo;
    }

    public String getTCodigo() {
        return tCodigo;
    }

    public void setTCodigo(String tCodigo) {
        this.tCodigo = tCodigo;
    }

    public String getTTipo() {
        return tTipo;
    }

    public void setTTipo(String tTipo) {
        this.tTipo = tTipo;
    }

    public String getTNombre() {
        return tNombre;
    }

    public void setTNombre(String tNombre) {
        this.tNombre = tNombre;
    }

    public String getTCiudad() {
        return tCiudad;
    }

    public void setTCiudad(String tCiudad) {
        this.tCiudad = tCiudad;
    }

    public Pais getTPais() {
        return tPais;
    }

    public void setTPais(Pais tPais) {
        this.tPais = tPais;
    }

    @XmlTransient
    public List<Vuelo> getTbVueloList() {
        return tbVueloList;
    }

    public void setTbVueloList(List<Vuelo> tbVueloList) {
        this.tbVueloList = tbVueloList;
    }

    @XmlTransient
    public List<Vuelo> getTbVueloList1() {
        return tbVueloList1;
    }

    public void setTbVueloList1(List<Vuelo> tbVueloList1) {
        this.tbVueloList1 = tbVueloList1;
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
        if (!(object instanceof Aeropuerto)) {
            return false;
        }
        Aeropuerto other = (Aeropuerto) object;
        if ((this.tCodigo == null && other.tCodigo != null) || (this.tCodigo != null && !this.tCodigo.equals(other.tCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.Aeropuerto[ tCodigo=" + tCodigo + " ]";
    }
    
}
