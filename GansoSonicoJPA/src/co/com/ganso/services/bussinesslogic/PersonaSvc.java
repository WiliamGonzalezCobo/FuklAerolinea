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
import co.com.ganso.entities.Operacion;
import co.com.ganso.entities.Persona;
import co.com.ganso.entities.PlanMillas;
import co.com.ganso.entities.Usuario;
import co.com.ganso.services.bussiness.IPersonaLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.IllegalOrphanException;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Stateless
public class PersonaSvc implements IPersonaLocalSvc {

	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;

    @Override
    public void create(Persona persona) throws PreexistingEntityException, Exception {
        if (persona.getTbOperacionList() == null) {
            persona.setTbOperacionList(new ArrayList<Operacion>());
        }
        if (persona.getTbUsuarioList() == null) {
            persona.setTbUsuarioList(new ArrayList<Usuario>());
        }
        try {
            em.getTransaction().begin();
            Categoria NCategoria = persona.getNCategoria();
            if (NCategoria != null) {
                NCategoria = em.getReference(NCategoria.getClass(), NCategoria.getNIdcategoria());
                persona.setNCategoria(NCategoria);
            }
            PlanMillas NPlanmilla = persona.getNPlanmilla();
            if (NPlanmilla != null) {
                NPlanmilla = em.getReference(NPlanmilla.getClass(), NPlanmilla.getNIdplanmilla());
                persona.setNPlanmilla(NPlanmilla);
            }
            List<Operacion> attachedTbOperacionList = new ArrayList<Operacion>();
            for (Operacion tbOperacionListOperacionToAttach : persona.getTbOperacionList()) {
                tbOperacionListOperacionToAttach = em.getReference(tbOperacionListOperacionToAttach.getClass(), tbOperacionListOperacionToAttach.getNIdoperacion());
                attachedTbOperacionList.add(tbOperacionListOperacionToAttach);
            }
            persona.setTbOperacionList(attachedTbOperacionList);
            List<Usuario> attachedTbUsuarioList = new ArrayList<Usuario>();
            for (Usuario tbUsuarioListUsuarioToAttach : persona.getTbUsuarioList()) {
                tbUsuarioListUsuarioToAttach = em.getReference(tbUsuarioListUsuarioToAttach.getClass(), tbUsuarioListUsuarioToAttach.getNIdusuario());
                attachedTbUsuarioList.add(tbUsuarioListUsuarioToAttach);
            }
            persona.setTbUsuarioList(attachedTbUsuarioList);
            em.persist(persona);
            if (NCategoria != null) {
                NCategoria.getTbPersonaList().add(persona);
                NCategoria = em.merge(NCategoria);
            }
            if (NPlanmilla != null) {
                NPlanmilla.getTbPersonaList().add(persona);
                NPlanmilla = em.merge(NPlanmilla);
            }
            for (Operacion tbOperacionListOperacion : persona.getTbOperacionList()) {
                Persona oldNPersonaOfTbOperacionListOperacion = tbOperacionListOperacion.getNPersona();
                tbOperacionListOperacion.setNPersona(persona);
                tbOperacionListOperacion = em.merge(tbOperacionListOperacion);
                if (oldNPersonaOfTbOperacionListOperacion != null) {
                    oldNPersonaOfTbOperacionListOperacion.getTbOperacionList().remove(tbOperacionListOperacion);
                    oldNPersonaOfTbOperacionListOperacion = em.merge(oldNPersonaOfTbOperacionListOperacion);
                }
            }
            for (Usuario tbUsuarioListUsuario : persona.getTbUsuarioList()) {
                Persona oldNPersonaOfTbUsuarioListUsuario = tbUsuarioListUsuario.getNPersona();
                tbUsuarioListUsuario.setNPersona(persona);
                tbUsuarioListUsuario = em.merge(tbUsuarioListUsuario);
                if (oldNPersonaOfTbUsuarioListUsuario != null) {
                    oldNPersonaOfTbUsuarioListUsuario.getTbUsuarioList().remove(tbUsuarioListUsuario);
                    oldNPersonaOfTbUsuarioListUsuario = em.merge(oldNPersonaOfTbUsuarioListUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersona(persona.getNIdpersona()) != null) {
                throw new PreexistingEntityException("Persona " + persona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Persona persona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getNIdpersona());
            Categoria NCategoriaOld = persistentPersona.getNCategoria();
            Categoria NCategoriaNew = persona.getNCategoria();
            PlanMillas NPlanmillaOld = persistentPersona.getNPlanmilla();
            PlanMillas NPlanmillaNew = persona.getNPlanmilla();
            List<Operacion> tbOperacionListOld = persistentPersona.getTbOperacionList();
            List<Operacion> tbOperacionListNew = persona.getTbOperacionList();
            List<Usuario> tbUsuarioListOld = persistentPersona.getTbUsuarioList();
            List<Usuario> tbUsuarioListNew = persona.getTbUsuarioList();
            List<String> illegalOrphanMessages = null;
            for (Operacion tbOperacionListOldOperacion : tbOperacionListOld) {
                if (!tbOperacionListNew.contains(tbOperacionListOldOperacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Operacion " + tbOperacionListOldOperacion + " since its NPersona field is not nullable.");
                }
            }
            for (Usuario tbUsuarioListOldUsuario : tbUsuarioListOld) {
                if (!tbUsuarioListNew.contains(tbUsuarioListOldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + tbUsuarioListOldUsuario + " since its NPersona field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (NCategoriaNew != null) {
                NCategoriaNew = em.getReference(NCategoriaNew.getClass(), NCategoriaNew.getNIdcategoria());
                persona.setNCategoria(NCategoriaNew);
            }
            if (NPlanmillaNew != null) {
                NPlanmillaNew = em.getReference(NPlanmillaNew.getClass(), NPlanmillaNew.getNIdplanmilla());
                persona.setNPlanmilla(NPlanmillaNew);
            }
            List<Operacion> attachedTbOperacionListNew = new ArrayList<Operacion>();
            for (Operacion tbOperacionListNewOperacionToAttach : tbOperacionListNew) {
                tbOperacionListNewOperacionToAttach = em.getReference(tbOperacionListNewOperacionToAttach.getClass(), tbOperacionListNewOperacionToAttach.getNIdoperacion());
                attachedTbOperacionListNew.add(tbOperacionListNewOperacionToAttach);
            }
            tbOperacionListNew = attachedTbOperacionListNew;
            persona.setTbOperacionList(tbOperacionListNew);
            List<Usuario> attachedTbUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario tbUsuarioListNewUsuarioToAttach : tbUsuarioListNew) {
                tbUsuarioListNewUsuarioToAttach = em.getReference(tbUsuarioListNewUsuarioToAttach.getClass(), tbUsuarioListNewUsuarioToAttach.getNIdusuario());
                attachedTbUsuarioListNew.add(tbUsuarioListNewUsuarioToAttach);
            }
            tbUsuarioListNew = attachedTbUsuarioListNew;
            persona.setTbUsuarioList(tbUsuarioListNew);
            persona = em.merge(persona);
            if (NCategoriaOld != null && !NCategoriaOld.equals(NCategoriaNew)) {
                NCategoriaOld.getTbPersonaList().remove(persona);
                NCategoriaOld = em.merge(NCategoriaOld);
            }
            if (NCategoriaNew != null && !NCategoriaNew.equals(NCategoriaOld)) {
                NCategoriaNew.getTbPersonaList().add(persona);
                NCategoriaNew = em.merge(NCategoriaNew);
            }
            if (NPlanmillaOld != null && !NPlanmillaOld.equals(NPlanmillaNew)) {
                NPlanmillaOld.getTbPersonaList().remove(persona);
                NPlanmillaOld = em.merge(NPlanmillaOld);
            }
            if (NPlanmillaNew != null && !NPlanmillaNew.equals(NPlanmillaOld)) {
                NPlanmillaNew.getTbPersonaList().add(persona);
                NPlanmillaNew = em.merge(NPlanmillaNew);
            }
            for (Operacion tbOperacionListNewOperacion : tbOperacionListNew) {
                if (!tbOperacionListOld.contains(tbOperacionListNewOperacion)) {
                    Persona oldNPersonaOfTbOperacionListNewOperacion = tbOperacionListNewOperacion.getNPersona();
                    tbOperacionListNewOperacion.setNPersona(persona);
                    tbOperacionListNewOperacion = em.merge(tbOperacionListNewOperacion);
                    if (oldNPersonaOfTbOperacionListNewOperacion != null && !oldNPersonaOfTbOperacionListNewOperacion.equals(persona)) {
                        oldNPersonaOfTbOperacionListNewOperacion.getTbOperacionList().remove(tbOperacionListNewOperacion);
                        oldNPersonaOfTbOperacionListNewOperacion = em.merge(oldNPersonaOfTbOperacionListNewOperacion);
                    }
                }
            }
            for (Usuario tbUsuarioListNewUsuario : tbUsuarioListNew) {
                if (!tbUsuarioListOld.contains(tbUsuarioListNewUsuario)) {
                    Persona oldNPersonaOfTbUsuarioListNewUsuario = tbUsuarioListNewUsuario.getNPersona();
                    tbUsuarioListNewUsuario.setNPersona(persona);
                    tbUsuarioListNewUsuario = em.merge(tbUsuarioListNewUsuario);
                    if (oldNPersonaOfTbUsuarioListNewUsuario != null && !oldNPersonaOfTbUsuarioListNewUsuario.equals(persona)) {
                        oldNPersonaOfTbUsuarioListNewUsuario.getTbUsuarioList().remove(tbUsuarioListNewUsuario);
                        oldNPersonaOfTbUsuarioListNewUsuario = em.merge(oldNPersonaOfTbUsuarioListNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = persona.getNIdpersona();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getNIdpersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Operacion> tbOperacionListOrphanCheck = persona.getTbOperacionList();
            for (Operacion tbOperacionListOrphanCheckOperacion : tbOperacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Operacion " + tbOperacionListOrphanCheckOperacion + " in its tbOperacionList field has a non-nullable NPersona field.");
            }
            List<Usuario> tbUsuarioListOrphanCheck = persona.getTbUsuarioList();
            for (Usuario tbUsuarioListOrphanCheckUsuario : tbUsuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Usuario " + tbUsuarioListOrphanCheckUsuario + " in its tbUsuarioList field has a non-nullable NPersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoria NCategoria = persona.getNCategoria();
            if (NCategoria != null) {
                NCategoria.getTbPersonaList().remove(persona);
                NCategoria = em.merge(NCategoria);
            }
            PlanMillas NPlanmilla = persona.getNPlanmilla();
            if (NPlanmilla != null) {
                NPlanmilla.getTbPersonaList().remove(persona);
                NPlanmilla = em.merge(NPlanmilla);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    @Override
    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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
    public Persona findPersona(BigDecimal id) {
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPersonaCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
