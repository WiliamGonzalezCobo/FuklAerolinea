/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussinesslogic;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import co.com.ganso.entities.Persona;
import co.com.ganso.entities.Usuario;
import co.com.ganso.services.bussiness.IUsuarioLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Stateless
public class UsuarioSvc implements IUsuarioLocalSvc {

	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;

    @Override
    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        try {
            em.getTransaction().begin();
            Persona NPersona = usuario.getNPersona();
            if (NPersona != null) {
                NPersona = em.getReference(NPersona.getClass(), NPersona.getNIdpersona());
                usuario.setNPersona(NPersona);
            }
            em.persist(usuario);
            if (NPersona != null) {
                NPersona.getTbUsuarioList().add(usuario);
                NPersona = em.merge(NPersona);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getNIdusuario()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getNIdusuario());
            Persona NPersonaOld = persistentUsuario.getNPersona();
            Persona NPersonaNew = usuario.getNPersona();
            if (NPersonaNew != null) {
                NPersonaNew = em.getReference(NPersonaNew.getClass(), NPersonaNew.getNIdpersona());
                usuario.setNPersona(NPersonaNew);
            }
            usuario = em.merge(usuario);
            if (NPersonaOld != null && !NPersonaOld.equals(NPersonaNew)) {
                NPersonaOld.getTbUsuarioList().remove(usuario);
                NPersonaOld = em.merge(NPersonaOld);
            }
            if (NPersonaNew != null && !NPersonaNew.equals(NPersonaOld)) {
                NPersonaNew.getTbUsuarioList().add(usuario);
                NPersonaNew = em.merge(NPersonaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = usuario.getNIdusuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy(BigDecimal id) throws NonexistentEntityException {
        try {
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getNIdusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Persona NPersona = usuario.getNPersona();
            if (NPersona != null) {
                NPersona.getTbUsuarioList().remove(usuario);
                NPersona = em.merge(NPersona);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    @Override
    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario findUsuario(BigDecimal id) {
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getUsuarioCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
