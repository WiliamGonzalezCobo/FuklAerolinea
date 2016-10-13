/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussiness;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.Operacion;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Local
public interface IOperacionLocalSvc {

	public void create(Operacion operacion) throws PreexistingEntityException, Exception;

	public void edit(Operacion operacion) throws NonexistentEntityException, Exception;

	public void destroy(BigDecimal id) throws NonexistentEntityException;

	public List<Operacion> findOperacionEntities();

	public List<Operacion> findOperacionEntities(int maxResults, int firstResult);

	public Operacion findOperacion(BigDecimal id);

	public int getOperacionCount();

}
