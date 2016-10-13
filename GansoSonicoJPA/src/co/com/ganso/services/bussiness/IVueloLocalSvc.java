/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussiness;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.Vuelo;
import co.com.ganso.services.bussinesslogic.exceptions.IllegalOrphanException;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Local
public interface IVueloLocalSvc {

	public void create(Vuelo vuelo) throws PreexistingEntityException, Exception;

	public void edit(Vuelo vuelo) throws IllegalOrphanException, NonexistentEntityException, Exception;

	public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException;

	public List<Vuelo> findVueloEntities();

	public List<Vuelo> findVueloEntities(int maxResults, int firstResult);

	public Vuelo findVuelo(BigDecimal id);

	public int getVueloCount();
}
