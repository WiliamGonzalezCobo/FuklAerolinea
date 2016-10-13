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
@Table(name = "TB_CATEGORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT t FROM Categoria t"),
    @NamedQuery(name = "Categoria.findByNIdcategoria", query = "SELECT t FROM Categoria t WHERE t.nIdcategoria = :nIdcategoria"),
    @NamedQuery(name = "Categoria.findByTNombre", query = "SELECT t FROM Categoria t WHERE t.tNombre = :tNombre"),
    @NamedQuery(name = "Categoria.findByTDescripcion", query = "SELECT t FROM Categoria t WHERE t.tDescripcion = :tDescripcion")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_IDCATEGORIA")
    private BigDecimal nIdcategoria;
    @Column(name = "T_NOMBRE")
    private String tNombre;
    @Column(name = "T_DESCRIPCION")
    private String tDescripcion;
    @OneToMany(mappedBy = "nCategoria", fetch = FetchType.EAGER)
    private List<Persona> tbPersonaList;

    public Categoria() {
    }

    public Categoria(BigDecimal nIdcategoria) {
        this.nIdcategoria = nIdcategoria;
    }

    public BigDecimal getNIdcategoria() {
        return nIdcategoria;
    }

    public void setNIdcategoria(BigDecimal nIdcategoria) {
        this.nIdcategoria = nIdcategoria;
    }

    public String getTNombre() {
        return tNombre;
    }

    public void setTNombre(String tNombre) {
        this.tNombre = tNombre;
    }

    public String getTDescripcion() {
        return tDescripcion;
    }

    public void setTDescripcion(String tDescripcion) {
        this.tDescripcion = tDescripcion;
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
        hash += (nIdcategoria != null ? nIdcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.nIdcategoria == null && other.nIdcategoria != null) || (this.nIdcategoria != null && !this.nIdcategoria.equals(other.nIdcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.Categoria[ nIdcategoria=" + nIdcategoria + " ]";
    }
    
}
