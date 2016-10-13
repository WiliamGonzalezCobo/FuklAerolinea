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

import co.com.ganso.entities.Categoria;
import co.com.ganso.entities.Persona;
import co.com.ganso.services.bussiness.ICategoriaLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Stateless
public class CategoriaSvc implements ICategoriaLocalSvc {

	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;
    
    @Override
    public void create(Categoria categoria) throws PreexistingEntityException, Exception {
        if (categoria.getTbPersonaList() == null) {
            categoria.setTbPersonaList(new ArrayList<Persona>());
        }
        try {
            em.getTransaction().begin();
            List<Persona> attachedTbPersonaList = new ArrayList<Persona>();
            for (Persona tbPersonaListPersonaToAttach : categoria.getTbPersonaList()) {
                tbPersonaListPersonaToAttach = em.getReference(tbPersonaListPersonaToAttach.getClass(), tbPersonaListPersonaToAttach.getNIdpersona());
                attachedTbPersonaList.add(tbPersonaListPersonaToAttach);
            }
            categoria.setTbPersonaList(attachedTbPersonaList);
            em.persist(categoria);
            for (Persona tbPersonaListPersona : categoria.getTbPersonaList()) {
                Categoria oldNCategoriaOfTbPersonaListPersona = tbPersonaListPersona.getNCategoria();
                tbPersonaListPersona.setNCategoria(categoria);
                tbPersonaListPersona = em.merge(tbPersonaListPersona);
                if (oldNCategoriaOfTbPersonaListPersona != null) {
                    oldNCategoriaOfTbPersonaListPersona.getTbPersonaList().remove(tbPersonaListPersona);
                    oldNCategoriaOfTbPersonaListPersona = em.merge(oldNCategoriaOfTbPersonaListPersona);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCategoria(categoria.getNIdcategoria()) != null) {
                throw new PreexistingEntityException("Categoria " + categoria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public void edit(Categoria categoria) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getNIdcategoria());
            List<Persona> tbPersonaListOld = persistentCategoria.getTbPersonaList();
            List<Persona> tbPersonaListNew = categoria.getTbPersonaList();
            List<Persona> attachedTbPersonaListNew = new ArrayList<Persona>();
            for (Persona tbPersonaListNewPersonaToAttach : tbPersonaListNew) {
                tbPersonaListNewPersonaToAttach = em.getReference(tbPersonaListNewPersonaToAttach.getClass(), tbPersonaListNewPersonaToAttach.getNIdpersona());
                attachedTbPersonaListNew.add(tbPersonaListNewPersonaToAttach);
            }
            tbPersonaListNew = attachedTbPersonaListNew;
            categoria.setTbPersonaList(tbPersonaListNew);
            categoria = em.merge(categoria);
            for (Persona tbPersonaListOldPersona : tbPersonaListOld) {
                if (!tbPersonaListNew.contains(tbPersonaListOldPersona)) {
                    tbPersonaListOldPersona.setNCategoria(null);
                    tbPersonaListOldPersona = em.merge(tbPersonaListOldPersona);
                }
            }
            for (Persona tbPersonaListNewPersona : tbPersonaListNew) {
                if (!tbPersonaListOld.contains(tbPersonaListNewPersona)) {
                    Categoria oldNCategoriaOfTbPersonaListNewPersona = tbPersonaListNewPersona.getNCategoria();
                    tbPersonaListNewPersona.setNCategoria(categoria);
                    tbPersonaListNewPersona = em.merge(tbPersonaListNewPersona);
                    if (oldNCategoriaOfTbPersonaListNewPersona != null && !oldNCategoriaOfTbPersonaListNewPersona.equals(categoria)) {
                        oldNCategoriaOfTbPersonaListNewPersona.getTbPersonaList().remove(tbPersonaListNewPersona);
                        oldNCategoriaOfTbPersonaListNewPersona = em.merge(oldNCategoriaOfTbPersonaListNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = categoria.getNIdcategoria();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
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
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getNIdcategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            List<Persona> tbPersonaList = categoria.getTbPersonaList();
            for (Persona tbPersonaListPersona : tbPersonaList) {
                tbPersonaListPersona.setNCategoria(null);
                tbPersonaListPersona = em.merge(tbPersonaListPersona);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }
    
    @Override
    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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
    public Categoria findCategoria(BigDecimal id) {
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }
    
    @Override
    public int getCategoriaCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
