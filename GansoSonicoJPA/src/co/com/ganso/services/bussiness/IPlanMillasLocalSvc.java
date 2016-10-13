/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussiness;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.PlanMillas;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Local
public interface IPlanMillasLocalSvc {

	public void create(PlanMillas planMillas) throws PreexistingEntityException, Exception;

	public void edit(PlanMillas planMillas) throws NonexistentEntityException, Exception;

	public void destroy(BigDecimal id) throws NonexistentEntityException;

	public List<PlanMillas> findPlanMillasEntities();

	public List<PlanMillas> findPlanMillasEntities(int maxResults, int firstResult);

	public PlanMillas findPlanMillas(BigDecimal id);

	public int getPlanMillasCount();

}
