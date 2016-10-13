/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussiness;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.Categoria;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Local
public interface ICategoriaLocalSvc{

    public void create(Categoria categoria) throws PreexistingEntityException, Exception;

    public void edit(Categoria categoria) throws NonexistentEntityException, Exception;

    public void destroy(BigDecimal id) throws NonexistentEntityException;

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult);
    
    public List<Categoria> findCategoriaEntities();

    public Categoria findCategoria(BigDecimal id);

    public int getCategoriaCount();
    
}
