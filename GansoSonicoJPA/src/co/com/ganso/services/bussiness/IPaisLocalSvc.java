/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussiness;

import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.Pais;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Local
public interface IPaisLocalSvc {

	public void create(Pais pais) throws PreexistingEntityException, Exception;

	public void edit(Pais pais) throws NonexistentEntityException, Exception;

	public void destroy(String id) throws NonexistentEntityException;

	public List<Pais> findPaisEntities();

	public List<Pais> findPaisEntities(int maxResults, int firstResult);

	public Pais findPais(String id);

	public int getPaisCount();
}
