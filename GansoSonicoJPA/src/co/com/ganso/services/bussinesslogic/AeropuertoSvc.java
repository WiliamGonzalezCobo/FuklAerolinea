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
import co.com.ganso.entities.Vuelo;
import co.com.ganso.services.bussiness.IAeropuertoLocalSvc;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */

@Stateless
public class AeropuertoSvc implements IAeropuertoLocalSvc{
	
	@PersistenceContext
    private EntityManager em;
	@PersistenceUnit(unitName=JPACons.PersistenceUnit)
	private EntityManagerFactory emf;

    
    @Override
    public void create(Aeropuerto aeropuerto) throws PreexistingEntityException, Exception {
        if (aeropuerto.getTbVueloList() == null) {
            aeropuerto.setTbVueloList(new ArrayList<Vuelo>());
        }
        if (aeropuerto.getTbVueloList1() == null) {
            aeropuerto.setTbVueloList1(new ArrayList<Vuelo>());
        }
        try {
            em.getTransaction().begin();
            Pais TPais = aeropuerto.getTPais();
            if (TPais != null) {
                TPais = em.getReference(TPais.getClass(), TPais.getTCodigo());
                aeropuerto.setTPais(TPais);
            }
            List<Vuelo> attachedTbVueloList = new ArrayList<Vuelo>();
            for (Vuelo tbVueloListVueloToAttach : aeropuerto.getTbVueloList()) {
                tbVueloListVueloToAttach = em.getReference(tbVueloListVueloToAttach.getClass(), tbVueloListVueloToAttach.getNIdvuelo());
                attachedTbVueloList.add(tbVueloListVueloToAttach);
            }
            aeropuerto.setTbVueloList(attachedTbVueloList);
            List<Vuelo> attachedTbVueloList1 = new ArrayList<Vuelo>();
            for (Vuelo tbVueloList1VueloToAttach : aeropuerto.getTbVueloList1()) {
                tbVueloList1VueloToAttach = em.getReference(tbVueloList1VueloToAttach.getClass(), tbVueloList1VueloToAttach.getNIdvuelo());
                attachedTbVueloList1.add(tbVueloList1VueloToAttach);
            }
            aeropuerto.setTbVueloList1(attachedTbVueloList1);
            em.persist(aeropuerto);
            if (TPais != null) {
                TPais.getTbAeropuertoList().add(aeropuerto);
                TPais = em.merge(TPais);
            }
            for (Vuelo tbVueloListVuelo : aeropuerto.getTbVueloList()) {
                Aeropuerto oldTOrigenOfTbVueloListVuelo = tbVueloListVuelo.getTOrigen();
                tbVueloListVuelo.setTOrigen(aeropuerto);
                tbVueloListVuelo = em.merge(tbVueloListVuelo);
                if (oldTOrigenOfTbVueloListVuelo != null) {
                    oldTOrigenOfTbVueloListVuelo.getTbVueloList().remove(tbVueloListVuelo);
                    oldTOrigenOfTbVueloListVuelo = em.merge(oldTOrigenOfTbVueloListVuelo);
                }
            }
            for (Vuelo tbVueloList1Vuelo : aeropuerto.getTbVueloList1()) {
                Aeropuerto oldTDestinoOfTbVueloList1Vuelo = tbVueloList1Vuelo.getTDestino();
                tbVueloList1Vuelo.setTDestino(aeropuerto);
                tbVueloList1Vuelo = em.merge(tbVueloList1Vuelo);
                if (oldTDestinoOfTbVueloList1Vuelo != null) {
                    oldTDestinoOfTbVueloList1Vuelo.getTbVueloList1().remove(tbVueloList1Vuelo);
                    oldTDestinoOfTbVueloList1Vuelo = em.merge(oldTDestinoOfTbVueloList1Vuelo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAeropuerto(aeropuerto.getTCodigo()) != null) {
                throw new PreexistingEntityException("Aeropuerto " + aeropuerto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public void edit(Aeropuerto aeropuerto) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            Aeropuerto persistentAeropuerto = em.find(Aeropuerto.class, aeropuerto.getTCodigo());
            Pais TPaisOld = persistentAeropuerto.getTPais();
            Pais TPaisNew = aeropuerto.getTPais();
            List<Vuelo> tbVueloListOld = persistentAeropuerto.getTbVueloList();
            List<Vuelo> tbVueloListNew = aeropuerto.getTbVueloList();
            List<Vuelo> tbVueloList1Old = persistentAeropuerto.getTbVueloList1();
            List<Vuelo> tbVueloList1New = aeropuerto.getTbVueloList1();
            if (TPaisNew != null) {
                TPaisNew = em.getReference(TPaisNew.getClass(), TPaisNew.getTCodigo());
                aeropuerto.setTPais(TPaisNew);
            }
            List<Vuelo> attachedTbVueloListNew = new ArrayList<Vuelo>();
            for (Vuelo tbVueloListNewVueloToAttach : tbVueloListNew) {
                tbVueloListNewVueloToAttach = em.getReference(tbVueloListNewVueloToAttach.getClass(), tbVueloListNewVueloToAttach.getNIdvuelo());
                attachedTbVueloListNew.add(tbVueloListNewVueloToAttach);
            }
            tbVueloListNew = attachedTbVueloListNew;
            aeropuerto.setTbVueloList(tbVueloListNew);
            List<Vuelo> attachedTbVueloList1New = new ArrayList<Vuelo>();
            for (Vuelo tbVueloList1NewVueloToAttach : tbVueloList1New) {
                tbVueloList1NewVueloToAttach = em.getReference(tbVueloList1NewVueloToAttach.getClass(), tbVueloList1NewVueloToAttach.getNIdvuelo());
                attachedTbVueloList1New.add(tbVueloList1NewVueloToAttach);
            }
            tbVueloList1New = attachedTbVueloList1New;
            aeropuerto.setTbVueloList1(tbVueloList1New);
            aeropuerto = em.merge(aeropuerto);
            if (TPaisOld != null && !TPaisOld.equals(TPaisNew)) {
                TPaisOld.getTbAeropuertoList().remove(aeropuerto);
                TPaisOld = em.merge(TPaisOld);
            }
            if (TPaisNew != null && !TPaisNew.equals(TPaisOld)) {
                TPaisNew.getTbAeropuertoList().add(aeropuerto);
                TPaisNew = em.merge(TPaisNew);
            }
            for (Vuelo tbVueloListOldVuelo : tbVueloListOld) {
                if (!tbVueloListNew.contains(tbVueloListOldVuelo)) {
                    tbVueloListOldVuelo.setTOrigen(null);
                    tbVueloListOldVuelo = em.merge(tbVueloListOldVuelo);
                }
            }
            for (Vuelo tbVueloListNewVuelo : tbVueloListNew) {
                if (!tbVueloListOld.contains(tbVueloListNewVuelo)) {
                    Aeropuerto oldTOrigenOfTbVueloListNewVuelo = tbVueloListNewVuelo.getTOrigen();
                    tbVueloListNewVuelo.setTOrigen(aeropuerto);
                    tbVueloListNewVuelo = em.merge(tbVueloListNewVuelo);
                    if (oldTOrigenOfTbVueloListNewVuelo != null && !oldTOrigenOfTbVueloListNewVuelo.equals(aeropuerto)) {
                        oldTOrigenOfTbVueloListNewVuelo.getTbVueloList().remove(tbVueloListNewVuelo);
                        oldTOrigenOfTbVueloListNewVuelo = em.merge(oldTOrigenOfTbVueloListNewVuelo);
                    }
                }
            }
            for (Vuelo tbVueloList1OldVuelo : tbVueloList1Old) {
                if (!tbVueloList1New.contains(tbVueloList1OldVuelo)) {
                    tbVueloList1OldVuelo.setTDestino(null);
                    tbVueloList1OldVuelo = em.merge(tbVueloList1OldVuelo);
                }
            }
            for (Vuelo tbVueloList1NewVuelo : tbVueloList1New) {
                if (!tbVueloList1Old.contains(tbVueloList1NewVuelo)) {
                    Aeropuerto oldTDestinoOfTbVueloList1NewVuelo = tbVueloList1NewVuelo.getTDestino();
                    tbVueloList1NewVuelo.setTDestino(aeropuerto);
                    tbVueloList1NewVuelo = em.merge(tbVueloList1NewVuelo);
                    if (oldTDestinoOfTbVueloList1NewVuelo != null && !oldTDestinoOfTbVueloList1NewVuelo.equals(aeropuerto)) {
                        oldTDestinoOfTbVueloList1NewVuelo.getTbVueloList1().remove(tbVueloList1NewVuelo);
                        oldTDestinoOfTbVueloList1NewVuelo = em.merge(oldTDestinoOfTbVueloList1NewVuelo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = aeropuerto.getTCodigo();
                if (findAeropuerto(id) == null) {
                    throw new NonexistentEntityException("The aeropuerto with id " + id + " no longer exists.");
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
            Aeropuerto aeropuerto;
            try {
                aeropuerto = em.getReference(Aeropuerto.class, id);
                aeropuerto.getTCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aeropuerto with id " + id + " no longer exists.", enfe);
            }
            Pais TPais = aeropuerto.getTPais();
            if (TPais != null) {
                TPais.getTbAeropuertoList().remove(aeropuerto);
                TPais = em.merge(TPais);
            }
            List<Vuelo> tbVueloList = aeropuerto.getTbVueloList();
            for (Vuelo tbVueloListVuelo : tbVueloList) {
                tbVueloListVuelo.setTOrigen(null);
                tbVueloListVuelo = em.merge(tbVueloListVuelo);
            }
            List<Vuelo> tbVueloList1 = aeropuerto.getTbVueloList1();
            for (Vuelo tbVueloList1Vuelo : tbVueloList1) {
                tbVueloList1Vuelo.setTDestino(null);
                tbVueloList1Vuelo = em.merge(tbVueloList1Vuelo);
            }
            em.remove(aeropuerto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public List<Aeropuerto> findAeropuertoEntities() {
        return findAeropuertoEntities(true, -1, -1);
    }
    
    @Override
    public List<Aeropuerto> findAeropuertoEntities(int maxResults, int firstResult) {
        return findAeropuertoEntities(false, maxResults, firstResult);
    }
    
    private List<Aeropuerto> findAeropuertoEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aeropuerto.class));
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
    public Aeropuerto findAeropuerto(String id) {
        try {
            return em.find(Aeropuerto.class, id);
        } finally {
            em.close();
        }
    }
    
    @Override
    public int getAeropuertoCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aeropuerto> rt = cq.from(Aeropuerto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
