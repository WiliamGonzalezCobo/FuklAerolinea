/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussiness;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.FactorPrecio;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Local
public interface IFactorPrecioLocalSvc {

	public void create(FactorPrecio factorPrecio) throws PreexistingEntityException, Exception;

	public void edit(FactorPrecio factorPrecio) throws NonexistentEntityException, Exception;

	public void destroy(BigDecimal id) throws NonexistentEntityException;

	public List<FactorPrecio> findFactorPrecioEntities();

	public List<FactorPrecio> findFactorPrecioEntities(int maxResults, int firstResult);

	public FactorPrecio findFactorPrecio(BigDecimal id);

	public int getFactorPrecioCount();

}
