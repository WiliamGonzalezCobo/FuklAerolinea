/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussinesslogic;

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

import co.com.ganso.entities.Aeropuerto;
import co.com.ganso.entities.Pais;
import co.com.ganso.services.bussiness.IPaisLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Stateless
public class PaisSvc implements IPaisLocalSvc {

	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;

    @Override
    public void create(Pais pais) throws PreexistingEntityException, Exception {
        if (pais.getTbAeropuertoList() == null) {
            pais.setTbAeropuertoList(new ArrayList<Aeropuerto>());
        }
        try {
            em.getTransaction().begin();
            List<Aeropuerto> attachedTbAeropuertoList = new ArrayList<Aeropuerto>();
            for (Aeropuerto tbAeropuertoListAeropuertoToAttach : pais.getTbAeropuertoList()) {
                tbAeropuertoListAeropuertoToAttach = em.getReference(tbAeropuertoListAeropuertoToAttach.getClass(), tbAeropuertoListAeropuertoToAttach.getTCodigo());
                attachedTbAeropuertoList.add(tbAeropuertoListAeropuertoToAttach);
            }
            pais.setTbAeropuertoList(attachedTbAeropuertoList);
            em.persist(pais);
            for (Aeropuerto tbAeropuertoListAeropuerto : pais.getTbAeropuertoList()) {
                Pais oldTPaisOfTbAeropuertoListAeropuerto = tbAeropuertoListAeropuerto.getTPais();
                tbAeropuertoListAeropuerto.setTPais(pais);
                tbAeropuertoListAeropuerto = em.merge(tbAeropuertoListAeropuerto);
                if (oldTPaisOfTbAeropuertoListAeropuerto != null) {
                    oldTPaisOfTbAeropuertoListAeropuerto.getTbAeropuertoList().remove(tbAeropuertoListAeropuerto);
                    oldTPaisOfTbAeropuertoListAeropuerto = em.merge(oldTPaisOfTbAeropuertoListAeropuerto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPais(pais.getTCodigo()) != null) {
                throw new PreexistingEntityException("Pais " + pais + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Pais pais) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            Pais persistentPais = em.find(Pais.class, pais.getTCodigo());
            List<Aeropuerto> tbAeropuertoListOld = persistentPais.getTbAeropuertoList();
            List<Aeropuerto> tbAeropuertoListNew = pais.getTbAeropuertoList();
            List<Aeropuerto> attachedTbAeropuertoListNew = new ArrayList<Aeropuerto>();
            for (Aeropuerto tbAeropuertoListNewAeropuertoToAttach : tbAeropuertoListNew) {
                tbAeropuertoListNewAeropuertoToAttach = em.getReference(tbAeropuertoListNewAeropuertoToAttach.getClass(), tbAeropuertoListNewAeropuertoToAttach.getTCodigo());
                attachedTbAeropuertoListNew.add(tbAeropuertoListNewAeropuertoToAttach);
            }
            tbAeropuertoListNew = attachedTbAeropuertoListNew;
            pais.setTbAeropuertoList(tbAeropuertoListNew);
            pais = em.merge(pais);
            for (Aeropuerto tbAeropuertoListOldAeropuerto : tbAeropuertoListOld) {
                if (!tbAeropuertoListNew.contains(tbAeropuertoListOldAeropuerto)) {
                    tbAeropuertoListOldAeropuerto.setTPais(null);
                    tbAeropuertoListOldAeropuerto = em.merge(tbAeropuertoListOldAeropuerto);
                }
            }
            for (Aeropuerto tbAeropuertoListNewAeropuerto : tbAeropuertoListNew) {
                if (!tbAeropuertoListOld.contains(tbAeropuertoListNewAeropuerto)) {
                    Pais oldTPaisOfTbAeropuertoListNewAeropuerto = tbAeropuertoListNewAeropuerto.getTPais();
                    tbAeropuertoListNewAeropuerto.setTPais(pais);
                    tbAeropuertoListNewAeropuerto = em.merge(tbAeropuertoListNewAeropuerto);
                    if (oldTPaisOfTbAeropuertoListNewAeropuerto != null && !oldTPaisOfTbAeropuertoListNewAeropuerto.equals(pais)) {
                        oldTPaisOfTbAeropuertoListNewAeropuerto.getTbAeropuertoList().remove(tbAeropuertoListNewAeropuerto);
                        oldTPaisOfTbAeropuertoListNewAeropuerto = em.merge(oldTPaisOfTbAeropuertoListNewAeropuerto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = pais.getTCodigo();
                if (findPais(id) == null) {
                    throw new NonexistentEntityException("The pais with id " + id + " no longer exists.");
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
    public void destroy(String id) throws NonexistentEntityException {
        try {
            em.getTransaction().begin();
            Pais pais;
            try {
                pais = em.getReference(Pais.class, id);
                pais.getTCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pais with id " + id + " no longer exists.", enfe);
            }
            List<Aeropuerto> tbAeropuertoList = pais.getTbAeropuertoList();
            for (Aeropuerto tbAeropuertoListAeropuerto : tbAeropuertoList) {
                tbAeropuertoListAeropuerto.setTPais(null);
                tbAeropuertoListAeropuerto = em.merge(tbAeropuertoListAeropuerto);
            }
            em.remove(pais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public List<Pais> findPaisEntities() {
        return findPaisEntities(true, -1, -1);
    }

    @Override
    public List<Pais> findPaisEntities(int maxResults, int firstResult) {
        return findPaisEntities(false, maxResults, firstResult);
    }

    private List<Pais> findPaisEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pais.class));
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
    public Pais findPais(String id) {
        try {
            return em.find(Pais.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPaisCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pais> rt = cq.from(Pais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
