/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "TB_PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT t FROM Persona t"),
    @NamedQuery(name = "Persona.findByNIdpersona", query = "SELECT t FROM Persona t WHERE t.nIdpersona = :nIdpersona"),
    @NamedQuery(name = "Persona.findByTNombres", query = "SELECT t FROM Persona t WHERE t.tNombres = :tNombres"),
    @NamedQuery(name = "Persona.findByTApellidos", query = "SELECT t FROM Persona t WHERE t.tApellidos = :tApellidos"),
    @NamedQuery(name = "Persona.findByDFechanacimiento", query = "SELECT t FROM Persona t WHERE t.dFechanacimiento = :dFechanacimiento"),
    @NamedQuery(name = "Persona.findByTDireccion", query = "SELECT t FROM Persona t WHERE t.tDireccion = :tDireccion"),
    @NamedQuery(name = "Persona.findByTTelefono", query = "SELECT t FROM Persona t WHERE t.tTelefono = :tTelefono"),
    @NamedQuery(name = "Persona.findByTPasaporte", query = "SELECT t FROM Persona t WHERE t.tPasaporte = :tPasaporte")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_IDPERSONA")
    private BigDecimal nIdpersona;
    @Column(name = "T_NOMBRES")
    private String tNombres;
    @Column(name = "T_APELLIDOS")
    private String tApellidos;
    @Column(name = "D_FECHANACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFechanacimiento;
    @Column(name = "T_DIRECCION")
    private String tDireccion;
    @Column(name = "T_TELEFONO")
    private String tTelefono;
    @Column(name = "T_PASAPORTE")
    private String tPasaporte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nPersona", fetch = FetchType.EAGER)
    private List<Operacion> tbOperacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nPersona", fetch = FetchType.EAGER)
    private List<Usuario> tbUsuarioList;
    @JoinColumn(name = "N_CATEGORIA", referencedColumnName = "N_IDCATEGORIA")
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria nCategoria;
    @JoinColumn(name = "N_PLANMILLA", referencedColumnName = "N_IDPLANMILLA")
    @ManyToOne(fetch = FetchType.EAGER)
    private PlanMillas nPlanmilla;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nPersona", fetch = FetchType.EAGER)
    private List<PersonaPremio> tbPersonapremioList;

    public Persona() {
    }

    public Persona(BigDecimal nIdpersona) {
        this.nIdpersona = nIdpersona;
    }

    public BigDecimal getNIdpersona() {
        return nIdpersona;
    }

    public void setNIdpersona(BigDecimal nIdpersona) {
        this.nIdpersona = nIdpersona;
    }

    public String getTNombres() {
        return tNombres;
    }

    public void setTNombres(String tNombres) {
        this.tNombres = tNombres;
    }

    public String getTApellidos() {
        return tApellidos;
    }

    public void setTApellidos(String tApellidos) {
        this.tApellidos = tApellidos;
    }

    public Date getDFechanacimiento() {
        return dFechanacimiento;
    }

    public void setDFechanacimiento(Date dFechanacimiento) {
        this.dFechanacimiento = dFechanacimiento;
    }

    public String getTDireccion() {
        return tDireccion;
    }

    public void setTDireccion(String tDireccion) {
        this.tDireccion = tDireccion;
    }

    public String getTTelefono() {
        return tTelefono;
    }

    public void setTTelefono(String tTelefono) {
        this.tTelefono = tTelefono;
    }

    public String getTPasaporte() {
        return tPasaporte;
    }

    public void setTPasaporte(String tPasaporte) {
        this.tPasaporte = tPasaporte;
    }

    @XmlTransient
    public List<Operacion> getTbOperacionList() {
        return tbOperacionList;
    }

    public void setTbOperacionList(List<Operacion> tbOperacionList) {
        this.tbOperacionList = tbOperacionList;
    }

    @XmlTransient
    public List<Usuario> getTbUsuarioList() {
        return tbUsuarioList;
    }

    public void setTbUsuarioList(List<Usuario> tbUsuarioList) {
        this.tbUsuarioList = tbUsuarioList;
    }

    public Categoria getNCategoria() {
        return nCategoria;
    }

    public void setNCategoria(Categoria nCategoria) {
        this.nCategoria = nCategoria;
    }

    public PlanMillas getNPlanmilla() {
        return nPlanmilla;
    }

    public void setNPlanmilla(PlanMillas nPlanmilla) {
        this.nPlanmilla = nPlanmilla;
    }

    @XmlTransient
    public List<PersonaPremio> getPersonapremioList() {
        return tbPersonapremioList;
    }

    public void setPersonaPremioList(List<PersonaPremio> tbPersonapremioList) {
        this.tbPersonapremioList = tbPersonapremioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdpersona != null ? nIdpersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.nIdpersona == null && other.nIdpersona != null) || (this.nIdpersona != null && !this.nIdpersona.equals(other.nIdpersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.Persona[ nIdpersona=" + nIdpersona + " ]";
    }
    
}
