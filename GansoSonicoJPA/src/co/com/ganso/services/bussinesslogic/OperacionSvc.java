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

import co.com.ganso.entities.ClaseVuelo;
import co.com.ganso.entities.Estado;
import co.com.ganso.entities.Operacion;
import co.com.ganso.entities.Persona;
import co.com.ganso.entities.Vuelo;
import co.com.ganso.services.bussiness.IOperacionLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Stateless
public class OperacionSvc implements IOperacionLocalSvc {

	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;

    @Override
    public void create(Operacion operacion) throws PreexistingEntityException, Exception {
        try {
            em.getTransaction().begin();
            ClaseVuelo NClase = operacion.getNClase();
            if (NClase != null) {
                NClase = em.getReference(NClase.getClass(), NClase.getNIdclase());
                operacion.setNClase(NClase);
            }
            Estado NEstado = operacion.getNEstado();
            if (NEstado != null) {
                NEstado = em.getReference(NEstado.getClass(), NEstado.getNIdestado());
                operacion.setNEstado(NEstado);
            }
            Persona NPersona = operacion.getNPersona();
            if (NPersona != null) {
                NPersona = em.getReference(NPersona.getClass(), NPersona.getNIdpersona());
                operacion.setNPersona(NPersona);
            }
            Vuelo NVuelo = operacion.getNVuelo();
            if (NVuelo != null) {
                NVuelo = em.getReference(NVuelo.getClass(), NVuelo.getNIdvuelo());
                operacion.setNVuelo(NVuelo);
            }
            em.persist(operacion);
            if (NClase != null) {
                NClase.getTbOperacionList().add(operacion);
                NClase = em.merge(NClase);
            }
            if (NEstado != null) {
                NEstado.getTbOperacionList().add(operacion);
                NEstado = em.merge(NEstado);
            }
            if (NPersona != null) {
                NPersona.getTbOperacionList().add(operacion);
                NPersona = em.merge(NPersona);
            }
            if (NVuelo != null) {
                NVuelo.getTbOperacionList().add(operacion);
                NVuelo = em.merge(NVuelo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOperacion(operacion.getNIdoperacion()) != null) {
                throw new PreexistingEntityException("Operacion " + operacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Operacion operacion) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            Operacion persistentOperacion = em.find(Operacion.class, operacion.getNIdoperacion());
            ClaseVuelo NClaseOld = persistentOperacion.getNClase();
            ClaseVuelo NClaseNew = operacion.getNClase();
            Estado NEstadoOld = persistentOperacion.getNEstado();
            Estado NEstadoNew = operacion.getNEstado();
            Persona NPersonaOld = persistentOperacion.getNPersona();
            Persona NPersonaNew = operacion.getNPersona();
            Vuelo NVueloOld = persistentOperacion.getNVuelo();
            Vuelo NVueloNew = operacion.getNVuelo();
            if (NClaseNew != null) {
                NClaseNew = em.getReference(NClaseNew.getClass(), NClaseNew.getNIdclase());
                operacion.setNClase(NClaseNew);
            }
            if (NEstadoNew != null) {
                NEstadoNew = em.getReference(NEstadoNew.getClass(), NEstadoNew.getNIdestado());
                operacion.setNEstado(NEstadoNew);
            }
            if (NPersonaNew != null) {
                NPersonaNew = em.getReference(NPersonaNew.getClass(), NPersonaNew.getNIdpersona());
                operacion.setNPersona(NPersonaNew);
            }
            if (NVueloNew != null) {
                NVueloNew = em.getReference(NVueloNew.getClass(), NVueloNew.getNIdvuelo());
                operacion.setNVuelo(NVueloNew);
            }
            operacion = em.merge(operacion);
            if (NClaseOld != null && !NClaseOld.equals(NClaseNew)) {
                NClaseOld.getTbOperacionList().remove(operacion);
                NClaseOld = em.merge(NClaseOld);
            }
            if (NClaseNew != null && !NClaseNew.equals(NClaseOld)) {
                NClaseNew.getTbOperacionList().add(operacion);
                NClaseNew = em.merge(NClaseNew);
            }
            if (NEstadoOld != null && !NEstadoOld.equals(NEstadoNew)) {
                NEstadoOld.getTbOperacionList().remove(operacion);
                NEstadoOld = em.merge(NEstadoOld);
            }
            if (NEstadoNew != null && !NEstadoNew.equals(NEstadoOld)) {
                NEstadoNew.getTbOperacionList().add(operacion);
                NEstadoNew = em.merge(NEstadoNew);
            }
            if (NPersonaOld != null && !NPersonaOld.equals(NPersonaNew)) {
                NPersonaOld.getTbOperacionList().remove(operacion);
                NPersonaOld = em.merge(NPersonaOld);
            }
            if (NPersonaNew != null && !NPersonaNew.equals(NPersonaOld)) {
                NPersonaNew.getTbOperacionList().add(operacion);
                NPersonaNew = em.merge(NPersonaNew);
            }
            if (NVueloOld != null && !NVueloOld.equals(NVueloNew)) {
                NVueloOld.getTbOperacionList().remove(operacion);
                NVueloOld = em.merge(NVueloOld);
            }
            if (NVueloNew != null && !NVueloNew.equals(NVueloOld)) {
                NVueloNew.getTbOperacionList().add(operacion);
                NVueloNew = em.merge(NVueloNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = operacion.getNIdoperacion();
                if (findOperacion(id) == null) {
                    throw new NonexistentEntityException("The operacion with id " + id + " no longer exists.");
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
            Operacion operacion;
            try {
                operacion = em.getReference(Operacion.class, id);
                operacion.getNIdoperacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The operacion with id " + id + " no longer exists.", enfe);
            }
            ClaseVuelo NClase = operacion.getNClase();
            if (NClase != null) {
                NClase.getTbOperacionList().remove(operacion);
                NClase = em.merge(NClase);
            }
            Estado NEstado = operacion.getNEstado();
            if (NEstado != null) {
                NEstado.getTbOperacionList().remove(operacion);
                NEstado = em.merge(NEstado);
            }
            Persona NPersona = operacion.getNPersona();
            if (NPersona != null) {
                NPersona.getTbOperacionList().remove(operacion);
                NPersona = em.merge(NPersona);
            }
            Vuelo NVuelo = operacion.getNVuelo();
            if (NVuelo != null) {
                NVuelo.getTbOperacionList().remove(operacion);
                NVuelo = em.merge(NVuelo);
            }
            em.remove(operacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Operacion> findOperacionEntities() {
        return findOperacionEntities(true, -1, -1);
    }

    @Override
    public List<Operacion> findOperacionEntities(int maxResults, int firstResult) {
        return findOperacionEntities(false, maxResults, firstResult);
    }

    private List<Operacion> findOperacionEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Operacion.class));
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
    public Operacion findOperacion(BigDecimal id) {
        try {
            return em.find(Operacion.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getOperacionCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Operacion> rt = cq.from(Operacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
