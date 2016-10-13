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
@Table(name = "TB_ESTADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT t FROM Estado t"),
    @NamedQuery(name = "Estado.findByNIdestado", query = "SELECT t FROM Estado t WHERE t.nIdestado = :nIdestado"),
    @NamedQuery(name = "Estado.findByTNombre", query = "SELECT t FROM Estado t WHERE t.tNombre = :tNombre"),
    @NamedQuery(name = "Estado.findByTDescripcion", query = "SELECT t FROM Estado t WHERE t.tDescripcion = :tDescripcion"),
    @NamedQuery(name = "Estado.findByTActivo", query = "SELECT t FROM Estado t WHERE t.tActivo = :tActivo")})
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_IDESTADO")
    private BigDecimal nIdestado;
    @Column(name = "T_NOMBRE")
    private String tNombre;
    @Column(name = "T_DESCRIPCION")
    private String tDescripcion;
    @Column(name = "T_ACTIVO")
    private Character tActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nEstado", fetch = FetchType.EAGER)
    private List<Operacion> tbOperacionList;

    public Estado() {
    }

    public Estado(BigDecimal nIdestado) {
        this.nIdestado = nIdestado;
    }

    public BigDecimal getNIdestado() {
        return nIdestado;
    }

    public void setNIdestado(BigDecimal nIdestado) {
        this.nIdestado = nIdestado;
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

    public Character getTActivo() {
        return tActivo;
    }

    public void setTActivo(Character tActivo) {
        this.tActivo = tActivo;
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
        hash += (nIdestado != null ? nIdestado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.nIdestado == null && other.nIdestado != null) || (this.nIdestado != null && !this.nIdestado.equals(other.nIdestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.Estado[ nIdestado=" + nIdestado + " ]";
    }
    
}
