package com.figaro.service;



import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.figaro.model.Movimiento;
import com.figaro.repository.MovimientosRepository;

public class MovimientosService {

	final static Logger LOGGER = Logger.getLogger(MovimientosService.class);
	
	private MovimientosRepository repository;

	public Movimiento getMovimiento(int movimientoID) {
		LOGGER.debug("Obteniendo el Movimiento con ID: " + movimientoID);
		return repository.getMovimiento(movimientoID);
	}

	public Movimiento saveMovimiento (Movimiento movimiento) {
		LOGGER.info("Guardando el Movimiento con ID: " + movimiento.getId()+" con:"+ movimiento.toString());
		int id = repository.saveMovimiento(movimiento);
		movimiento.setId(id);	
		return movimiento;
	}	
	
	public Movimiento updateMovimiento(Movimiento movimiento) {
		Movimiento old = getMovimiento(movimiento.getId());	
		LOGGER.info("Actualizando el Movimiento con ID: " + old.getId()+" con:"+ movimiento.toString());	
		old.update(movimiento);
		repository.updateMovimiento(old);
		return movimiento;
	}
	
	public Movimiento deleteMovimiento(int movimientoID) {
		Movimiento movimiento = getMovimiento(movimientoID);
		repository.deleteMovimiento(movimiento);
		return movimiento;
	}
	
	public MovimientosRepository getRepository() {
		return repository;
	}
	public void setRepository(MovimientosRepository repository) {
		this.repository = repository;
	}

	public List<Movimiento> getAllMovimiento() {
		LOGGER.debug("Obteniendo todos los movimientos");
		return repository.getAll();
	}
	
	public List<Movimiento> buscar(String search) {
		return repository.buscar(search);
	}
	
	public List<Movimiento> buscarE(Date search1, Date search2) {		
		return repository.buscarE(search1,search2);
	}
	
	public List<Movimiento> buscarCategoria(String search) {
		return repository.buscarCategoria(search);
	}	
	
	public List<Movimiento> buscarEC(Date search1, Date search2, String searchC) {		
		return repository.buscarEC(search1,search2,searchC);
	}
	
}