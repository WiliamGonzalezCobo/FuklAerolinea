/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussiness;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.PersonaPremio;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Local
public interface IPersonaPremioLocalSvc {

	public void create(PersonaPremio personaPremio) throws PreexistingEntityException, Exception;

	public void edit(PersonaPremio personaPremio) throws NonexistentEntityException, Exception;

	public void destroy(BigDecimal id) throws NonexistentEntityException;

	public List<PersonaPremio> findPersonaPremioEntities();

	public List<PersonaPremio> findPersonaPremioEntities(int maxResults, int firstResult);

	public PersonaPremio findPersonaPremio(BigDecimal id);

	public int getPersonaPremioCount();

}
