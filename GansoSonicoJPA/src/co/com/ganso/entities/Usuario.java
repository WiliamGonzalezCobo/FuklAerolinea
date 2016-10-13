/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "TB_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT t FROM Usuario t"),
    @NamedQuery(name = "Usuario.findByNIdusuario", query = "SELECT t FROM Usuario t WHERE t.nIdusuario = :nIdusuario"),
    @NamedQuery(name = "Usuario.findByTLogin", query = "SELECT t FROM Usuario t WHERE t.tLogin = :tLogin"),
    @NamedQuery(name = "Usuario.findByTPass", query = "SELECT t FROM Usuario t WHERE t.tPass = :tPass"),
    @NamedQuery(name = "Usuario.findByTCorreo", query = "SELECT t FROM Usuario t WHERE t.tCorreo = :tCorreo"),
    @NamedQuery(name = "Usuario.findByTActivo", query = "SELECT t FROM Usuario t WHERE t.tActivo = :tActivo")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_IDUSUARIO")
    private BigDecimal nIdusuario;
    @Basic(optional = false)
    @Column(name = "T_LOGIN")
    private String tLogin;
    @Basic(optional = false)
    @Column(name = "T_PASS")
    private String tPass;
    @Basic(optional = false)
    @Column(name = "T_CORREO")
    private String tCorreo;
    @Column(name = "T_ACTIVO")
    private Character tActivo;
    @JoinColumn(name = "N_PERSONA", referencedColumnName = "N_IDPERSONA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Persona nPersona;

    public Usuario() {
    }

    public Usuario(BigDecimal nIdusuario) {
        this.nIdusuario = nIdusuario;
    }

    public Usuario(BigDecimal nIdusuario, String tLogin, String tPass, String tCorreo) {
        this.nIdusuario = nIdusuario;
        this.tLogin = tLogin;
        this.tPass = tPass;
        this.tCorreo = tCorreo;
    }

    public BigDecimal getNIdusuario() {
        return nIdusuario;
    }

    public void setNIdusuario(BigDecimal nIdusuario) {
        this.nIdusuario = nIdusuario;
    }

    public String getTLogin() {
        return tLogin;
    }

    public void setTLogin(String tLogin) {
        this.tLogin = tLogin;
    }

    public String getTPass() {
        return tPass;
    }

    public void setTPass(String tPass) {
        this.tPass = tPass;
    }

    public String getTCorreo() {
        return tCorreo;
    }

    public void setTCorreo(String tCorreo) {
        this.tCorreo = tCorreo;
    }

    public Character getTActivo() {
        return tActivo;
    }

    public void setTActivo(Character tActivo) {
        this.tActivo = tActivo;
    }

    public Persona getNPersona() {
        return nPersona;
    }

    public void setNPersona(Persona nPersona) {
        this.nPersona = nPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdusuario != null ? nIdusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.nIdusuario == null && other.nIdusuario != null) || (this.nIdusuario != null && !this.nIdusuario.equals(other.nIdusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.ganso.entities.Usuario[ nIdusuario=" + nIdusuario + " ]";
    }
    
}
