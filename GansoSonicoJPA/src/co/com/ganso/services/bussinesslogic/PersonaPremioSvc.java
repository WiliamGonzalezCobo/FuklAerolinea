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
import co.com.ganso.entities.PersonaPremio;
import co.com.ganso.entities.Premio;
import co.com.ganso.services.bussiness.IPersonaPremioLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Stateless
public class PersonaPremioSvc implements IPersonaPremioLocalSvc {

	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;

    @Override
    public void create(PersonaPremio personaPremio) throws PreexistingEntityException, Exception {
        try {
            em.getTransaction().begin();
            Persona NPersona = personaPremio.getNPersona();
            if (NPersona != null) {
                NPersona = em.getReference(NPersona.getClass(), NPersona.getNIdpersona());
                personaPremio.setNPersona(NPersona);
            }
            Premio NPremio = personaPremio.getNPremio();
            if (NPremio != null) {
                NPremio = em.getReference(NPremio.getClass(), NPremio.getNIdpremio());
                personaPremio.setNPremio(NPremio);
            }
            em.persist(personaPremio);
            if (NPersona != null) {
                NPersona.getPersonapremioList().add(personaPremio);
                NPersona = em.merge(NPersona);
            }
            if (NPremio != null) {
                NPremio.getTbPersonapremioList().add(personaPremio);
                NPremio = em.merge(NPremio);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersonaPremio(personaPremio.getNIdpersonapremio()) != null) {
                throw new PreexistingEntityException("PersonaPremio " + personaPremio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(PersonaPremio personaPremio) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            PersonaPremio persistentPersonaPremio = em.find(PersonaPremio.class, personaPremio.getNIdpersonapremio());
            Persona NPersonaOld = persistentPersonaPremio.getNPersona();
            Persona NPersonaNew = personaPremio.getNPersona();
            Premio NPremioOld = persistentPersonaPremio.getNPremio();
            Premio NPremioNew = personaPremio.getNPremio();
            if (NPersonaNew != null) {
                NPersonaNew = em.getReference(NPersonaNew.getClass(), NPersonaNew.getNIdpersona());
                personaPremio.setNPersona(NPersonaNew);
            }
            if (NPremioNew != null) {
                NPremioNew = em.getReference(NPremioNew.getClass(), NPremioNew.getNIdpremio());
                personaPremio.setNPremio(NPremioNew);
            }
            personaPremio = em.merge(personaPremio);
            if (NPersonaOld != null && !NPersonaOld.equals(NPersonaNew)) {
                NPersonaOld.getPersonapremioList().remove(personaPremio);
                NPersonaOld = em.merge(NPersonaOld);
            }
            if (NPersonaNew != null && !NPersonaNew.equals(NPersonaOld)) {
                NPersonaNew.getPersonapremioList().add(personaPremio);
                NPersonaNew = em.merge(NPersonaNew);
            }
            if (NPremioOld != null && !NPremioOld.equals(NPremioNew)) {
                NPremioOld.getTbPersonapremioList().remove(personaPremio);
                NPremioOld = em.merge(NPremioOld);
            }
            if (NPremioNew != null && !NPremioNew.equals(NPremioOld)) {
                NPremioNew.getTbPersonapremioList().add(personaPremio);
                NPremioNew = em.merge(NPremioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = personaPremio.getNIdpersonapremio();
                if (findPersonaPremio(id) == null) {
                    throw new NonexistentEntityException("The personaPremio with id " + id + " no longer exists.");
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
            PersonaPremio personaPremio;
            try {
                personaPremio = em.getReference(PersonaPremio.class, id);
                personaPremio.getNIdpersonapremio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personaPremio with id " + id + " no longer exists.", enfe);
            }
            Persona NPersona = personaPremio.getNPersona();
            if (NPersona != null) {
                NPersona.getPersonapremioList().remove(personaPremio);
                NPersona = em.merge(NPersona);
            }
            Premio NPremio = personaPremio.getNPremio();
            if (NPremio != null) {
                NPremio.getTbPersonapremioList().remove(personaPremio);
                NPremio = em.merge(NPremio);
            }
            em.remove(personaPremio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<PersonaPremio> findPersonaPremioEntities() {
        return findPersonaPremioEntities(true, -1, -1);
    }

    @Override
    public List<PersonaPremio> findPersonaPremioEntities(int maxResults, int firstResult) {
        return findPersonaPremioEntities(false, maxResults, firstResult);
    }

    private List<PersonaPremio> findPersonaPremioEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersonaPremio.class));
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
    public PersonaPremio findPersonaPremio(BigDecimal id) {
        try {
            return em.find(PersonaPremio.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPersonaPremioCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersonaPremio> rt = cq.from(PersonaPremio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
