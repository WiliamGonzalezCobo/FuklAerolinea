/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussiness;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.Usuario;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */
@Local
public interface IUsuarioLocalSvc {

	public void create(Usuario usuario) throws PreexistingEntityException, Exception;

	public void edit(Usuario usuario) throws NonexistentEntityException, Exception;

	public void destroy(BigDecimal id) throws NonexistentEntityException;

	public List<Usuario> findUsuarioEntities();

	public List<Usuario> findUsuarioEntities(int maxResults, int firstResult);

	public Usuario findUsuario(BigDecimal id);

	public int getUsuarioCount();

}
