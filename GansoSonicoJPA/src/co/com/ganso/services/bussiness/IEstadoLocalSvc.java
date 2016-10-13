/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussiness;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.Estado;
import co.com.ganso.services.bussinesslogic.exceptions.IllegalOrphanException;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */

@Local
public interface IEstadoLocalSvc {

	public void create(Estado estado) throws PreexistingEntityException, Exception;

	public void edit(Estado estado) throws IllegalOrphanException, NonexistentEntityException, Exception;

	public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException;

	public List<Estado> findEstadoEntities();

	public List<Estado> findEstadoEntities(int maxResults, int firstResult);

	public Estado findEstado(BigDecimal id);

	public int getEstadoCount();

}
