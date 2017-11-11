package com.figaro.controllerREST;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.figaro.model.Movimiento;
import com.figaro.service.MovimientosService;

@RestController
@RequestMapping(value = "/rest/")
public class MovimientosControllerREST {
	
	@Autowired
	@Qualifier("MovimientosServiceTransactional")
	private MovimientosService service;

	@RequestMapping(value = "movimientos/{movimientoID}",method=RequestMethod.GET,produces="application/json")
    public Movimiento getMovimiento( @PathVariable int movimientoID) {
        return service.getMovimiento(movimientoID);
    }
	
	@RequestMapping(value = "movimientos/alta",method=RequestMethod.POST)
    public ResponseEntity<Movimiento> newMovimiento(@RequestBody Movimiento movimiento) {
		return new ResponseEntity<Movimiento>(service.saveMovimiento(movimiento), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "movimientos/actualizar/{movimientoID}",method=RequestMethod.PUT)
    public ResponseEntity<Movimiento> updateMovimiento(@RequestBody Movimiento movimiento) {
		Movimiento updated = service.updateMovimiento(movimiento);
		return new ResponseEntity<Movimiento>(updated, HttpStatus.OK);
	}
	
	@RequestMapping(value = "movimientos/buscar",method=RequestMethod.GET,produces="application/json")
    public ResponseEntity<List<Movimiento>> getAllMovimiento(@RequestParam  @DateTimeFormat(pattern="yyyy-MM-dd") Date q1, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date q2, @RequestParam String q3 ) {
        return new ResponseEntity<List<Movimiento>> (service.buscar(q1,q2,q3),HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "movimientos/eliminar/{movimientoID}",method=RequestMethod.DELETE)	
    public ResponseEntity<Movimiento> getAllMovimiento(@PathVariable int movimientoID) {
		return new ResponseEntity<>(service.deleteMovimiento(movimientoID), HttpStatus.OK);
	}
	
	public MovimientosService getService() {
		return service;
	}
	
	public void setService(MovimientosService service) {
		this.service = service;
	}
}