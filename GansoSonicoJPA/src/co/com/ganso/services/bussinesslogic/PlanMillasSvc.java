/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussinesslogic;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import co.com.ganso.entities.PlanMillas;
import co.com.ganso.services.bussiness.IPlanMillasLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Stateless
public class PlanMillasSvc implements IPlanMillasLocalSvc {

	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;

    @Override
    public void create(PlanMillas planMillas) throws PreexistingEntityException, Exception {
        if (planMillas.getTbPersonaList() == null) {
            planMillas.setTbPersonaList(new ArrayList<Persona>());
        }
        try {
            em.getTransaction().begin();
            List<Persona> attachedTbPersonaList = new ArrayList<Persona>();
            for (Persona tbPersonaListPersonaToAttach : planMillas.getTbPersonaList()) {
                tbPersonaListPersonaToAttach = em.getReference(tbPersonaListPersonaToAttach.getClass(), tbPersonaListPersonaToAttach.getNIdpersona());
                attachedTbPersonaList.add(tbPersonaListPersonaToAttach);
            }
            planMillas.setTbPersonaList(attachedTbPersonaList);
            em.persist(planMillas);
            for (Persona tbPersonaListPersona : planMillas.getTbPersonaList()) {
                PlanMillas oldNPlanmillaOfTbPersonaListPersona = tbPersonaListPersona.getNPlanmilla();
                tbPersonaListPersona.setNPlanmilla(planMillas);
                tbPersonaListPersona = em.merge(tbPersonaListPersona);
                if (oldNPlanmillaOfTbPersonaListPersona != null) {
                    oldNPlanmillaOfTbPersonaListPersona.getTbPersonaList().remove(tbPersonaListPersona);
                    oldNPlanmillaOfTbPersonaListPersona = em.merge(oldNPlanmillaOfTbPersonaListPersona);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanMillas(planMillas.getNIdplanmilla()) != null) {
                throw new PreexistingEntityException("PlanMillas " + planMillas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(PlanMillas planMillas) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            PlanMillas persistentPlanMillas = em.find(PlanMillas.class, planMillas.getNIdplanmilla());
            List<Persona> tbPersonaListOld = persistentPlanMillas.getTbPersonaList();
            List<Persona> tbPersonaListNew = planMillas.getTbPersonaList();
            List<Persona> attachedTbPersonaListNew = new ArrayList<Persona>();
            for (Persona tbPersonaListNewPersonaToAttach : tbPersonaListNew) {
                tbPersonaListNewPersonaToAttach = em.getReference(tbPersonaListNewPersonaToAttach.getClass(), tbPersonaListNewPersonaToAttach.getNIdpersona());
                attachedTbPersonaListNew.add(tbPersonaListNewPersonaToAttach);
            }
            tbPersonaListNew = attachedTbPersonaListNew;
            planMillas.setTbPersonaList(tbPersonaListNew);
            planMillas = em.merge(planMillas);
            for (Persona tbPersonaListOldPersona : tbPersonaListOld) {
                if (!tbPersonaListNew.contains(tbPersonaListOldPersona)) {
                    tbPersonaListOldPersona.setNPlanmilla(null);
                    tbPersonaListOldPersona = em.merge(tbPersonaListOldPersona);
                }
            }
            for (Persona tbPersonaListNewPersona : tbPersonaListNew) {
                if (!tbPersonaListOld.contains(tbPersonaListNewPersona)) {
                    PlanMillas oldNPlanmillaOfTbPersonaListNewPersona = tbPersonaListNewPersona.getNPlanmilla();
                    tbPersonaListNewPersona.setNPlanmilla(planMillas);
                    tbPersonaListNewPersona = em.merge(tbPersonaListNewPersona);
                    if (oldNPlanmillaOfTbPersonaListNewPersona != null && !oldNPlanmillaOfTbPersonaListNewPersona.equals(planMillas)) {
                        oldNPlanmillaOfTbPersonaListNewPersona.getTbPersonaList().remove(tbPersonaListNewPersona);
                        oldNPlanmillaOfTbPersonaListNewPersona = em.merge(oldNPlanmillaOfTbPersonaListNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = planMillas.getNIdplanmilla();
                if (findPlanMillas(id) == null) {
                    throw new NonexistentEntityException("The planMillas with id " + id + " no longer exists.");
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
            PlanMillas planMillas;
            try {
                planMillas = em.getReference(PlanMillas.class, id);
                planMillas.getNIdplanmilla();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planMillas with id " + id + " no longer exists.", enfe);
            }
            List<Persona> tbPersonaList = planMillas.getTbPersonaList();
            for (Persona tbPersonaListPersona : tbPersonaList) {
                tbPersonaListPersona.setNPlanmilla(null);
                tbPersonaListPersona = em.merge(tbPersonaListPersona);
            }
            em.remove(planMillas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<PlanMillas> findPlanMillasEntities() {
        return findPlanMillasEntities(true, -1, -1);
    }

    @Override
    public List<PlanMillas> findPlanMillasEntities(int maxResults, int firstResult) {
        return findPlanMillasEntities(false, maxResults, firstResult);
    }

    private List<PlanMillas> findPlanMillasEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PlanMillas.class));
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
    public PlanMillas findPlanMillas(BigDecimal id) {
        try {
            return em.find(PlanMillas.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPlanMillasCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PlanMillas> rt = cq.from(PlanMillas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
