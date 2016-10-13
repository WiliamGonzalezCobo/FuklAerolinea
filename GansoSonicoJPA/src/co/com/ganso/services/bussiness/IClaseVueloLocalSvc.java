/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussiness;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.ClaseVuelo;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Local
public interface IClaseVueloLocalSvc {
	
	public void create(ClaseVuelo claseVuelo) throws PreexistingEntityException, Exception;

	public void edit(ClaseVuelo claseVuelo) throws NonexistentEntityException, Exception;

	public void destroy(BigDecimal id) throws NonexistentEntityException;

	public List<ClaseVuelo> findClaseVueloEntities();

	public List<ClaseVuelo> findClaseVueloEntities(int maxResults, int firstResult);

	public ClaseVuelo findClaseVuelo(BigDecimal id);

	public int getClaseVueloCount();

}
