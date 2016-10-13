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

import co.com.ganso.entities.PersonaPremio;
import co.com.ganso.entities.Premio;
import co.com.ganso.services.bussiness.IPremioLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.IllegalOrphanException;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Stateless
public class PremioSvc implements IPremioLocalSvc {

	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;

    @Override
    public void create(Premio premio) throws PreexistingEntityException, Exception {
        if (premio.getTbPersonapremioList() == null) {
            premio.setTbPersonapremioList(new ArrayList<PersonaPremio>());
        }
        try {
            em.getTransaction().begin();
            List<PersonaPremio> attachedTbPersonapremioList = new ArrayList<PersonaPremio>();
            for (PersonaPremio tbPersonapremioListPersonaPremioToAttach : premio.getTbPersonapremioList()) {
                tbPersonapremioListPersonaPremioToAttach = em.getReference(tbPersonapremioListPersonaPremioToAttach.getClass(), tbPersonapremioListPersonaPremioToAttach.getNIdpersonapremio());
                attachedTbPersonapremioList.add(tbPersonapremioListPersonaPremioToAttach);
            }
            premio.setTbPersonapremioList(attachedTbPersonapremioList);
            em.persist(premio);
            for (PersonaPremio tbPersonapremioListPersonaPremio : premio.getTbPersonapremioList()) {
                Premio oldNPremioOfTbPersonapremioListPersonaPremio = tbPersonapremioListPersonaPremio.getNPremio();
                tbPersonapremioListPersonaPremio.setNPremio(premio);
                tbPersonapremioListPersonaPremio = em.merge(tbPersonapremioListPersonaPremio);
                if (oldNPremioOfTbPersonapremioListPersonaPremio != null) {
                    oldNPremioOfTbPersonapremioListPersonaPremio.getTbPersonapremioList().remove(tbPersonapremioListPersonaPremio);
                    oldNPremioOfTbPersonapremioListPersonaPremio = em.merge(oldNPremioOfTbPersonapremioListPersonaPremio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPremio(premio.getNIdpremio()) != null) {
                throw new PreexistingEntityException("Premio " + premio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Premio premio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            Premio persistentPremio = em.find(Premio.class, premio.getNIdpremio());
            List<PersonaPremio> tbPersonapremioListOld = persistentPremio.getTbPersonapremioList();
            List<PersonaPremio> tbPersonapremioListNew = premio.getTbPersonapremioList();
            List<String> illegalOrphanMessages = null;
            for (PersonaPremio tbPersonapremioListOldPersonaPremio : tbPersonapremioListOld) {
                if (!tbPersonapremioListNew.contains(tbPersonapremioListOldPersonaPremio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PersonaPremio " + tbPersonapremioListOldPersonaPremio + " since its NPremio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<PersonaPremio> attachedTbPersonapremioListNew = new ArrayList<PersonaPremio>();
            for (PersonaPremio tbPersonapremioListNewPersonaPremioToAttach : tbPersonapremioListNew) {
                tbPersonapremioListNewPersonaPremioToAttach = em.getReference(tbPersonapremioListNewPersonaPremioToAttach.getClass(), tbPersonapremioListNewPersonaPremioToAttach.getNIdpersonapremio());
                attachedTbPersonapremioListNew.add(tbPersonapremioListNewPersonaPremioToAttach);
            }
            tbPersonapremioListNew = attachedTbPersonapremioListNew;
            premio.setTbPersonapremioList(tbPersonapremioListNew);
            premio = em.merge(premio);
            for (PersonaPremio tbPersonapremioListNewPersonaPremio : tbPersonapremioListNew) {
                if (!tbPersonapremioListOld.contains(tbPersonapremioListNewPersonaPremio)) {
                    Premio oldNPremioOfTbPersonapremioListNewPersonaPremio = tbPersonapremioListNewPersonaPremio.getNPremio();
                    tbPersonapremioListNewPersonaPremio.setNPremio(premio);
                    tbPersonapremioListNewPersonaPremio = em.merge(tbPersonapremioListNewPersonaPremio);
                    if (oldNPremioOfTbPersonapremioListNewPersonaPremio != null && !oldNPremioOfTbPersonapremioListNewPersonaPremio.equals(premio)) {
                        oldNPremioOfTbPersonapremioListNewPersonaPremio.getTbPersonapremioList().remove(tbPersonapremioListNewPersonaPremio);
                        oldNPremioOfTbPersonapremioListNewPersonaPremio = em.merge(oldNPremioOfTbPersonapremioListNewPersonaPremio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = premio.getNIdpremio();
                if (findPremio(id) == null) {
                    throw new NonexistentEntityException("The premio with id " + id + " no longer exists.");
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
    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        try {
            em.getTransaction().begin();
            Premio premio;
            try {
                premio = em.getReference(Premio.class, id);
                premio.getNIdpremio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The premio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PersonaPremio> tbPersonapremioListOrphanCheck = premio.getTbPersonapremioList();
            for (PersonaPremio tbPersonapremioListOrphanCheckPersonaPremio : tbPersonapremioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Premio (" + premio + ") cannot be destroyed since the PersonaPremio " + tbPersonapremioListOrphanCheckPersonaPremio + " in its tbPersonapremioList field has a non-nullable NPremio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(premio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Premio> findPremioEntities() {
        return findPremioEntities(true, -1, -1);
    }

    @Override
    public List<Premio> findPremioEntities(int maxResults, int firstResult) {
        return findPremioEntities(false, maxResults, firstResult);
    }

    private List<Premio> findPremioEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Premio.class));
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
    public Premio findPremio(BigDecimal id) {
        try {
            return em.find(Premio.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPremioCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Premio> rt = cq.from(Premio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
