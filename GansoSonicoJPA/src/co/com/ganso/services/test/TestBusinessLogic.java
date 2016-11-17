package co.com.ganso.services.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import co.com.ganso.entities.Aeropuerto;
import co.com.ganso.services.bussinesslogic.ManagerSvc;

public class TestBusinessLogic {
	
	ManagerSvc manager;
	
	@Before
	public void setUp(){
		manager = new ManagerSvc();
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Test
	public void testCargarListaAeropuertosSeaMayorQueCero(){
		//// Act
		Aeropuerto aeropuerto = new Aeropuerto();
		List<Aeropuerto> lista;
		
		//// Arrange
		lista = (List<Aeropuerto>) manager.findAll(aeropuerto.getClass());
		
		//// Assert
		assertTrue(lista.size() > 0);
	}
	
}
