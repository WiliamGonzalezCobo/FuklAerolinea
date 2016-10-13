/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ganso.services.bussiness;

import java.util.List;

import javax.ejb.Local;

import co.com.ganso.entities.Aeropuerto;
import co.com.ganso.services.bussinesslogic.exceptions.NonexistentEntityException;
import co.com.ganso.services.bussinesslogic.exceptions.PreexistingEntityException;

/**
 *
 * @author juanc
 */

@Local
public interface IAeropuertoLocalSvc {

    public void create(Aeropuerto aeropuerto) throws PreexistingEntityException, Exception;

    public void edit(Aeropuerto aeropuerto) throws NonexistentEntityException, Exception;

    public void destroy(String id) throws NonexistentEntityException;

    public List<Aeropuerto> findAeropuertoEntities() ;

    public List<Aeropuerto> findAeropuertoEntities(int maxResults, int firstResult);

    public Aeropuerto findAeropuerto(String id);

    public int getAeropuertoCount();
    
}
