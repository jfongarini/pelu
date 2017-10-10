package com.figaro.controllerREST;

import java.util.Date;

import java.util.List;

import static com.figaro.util.Constant.*;
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

import com.figaro.model.Turno;
import com.figaro.service.TurnosService;

@RestController
@RequestMapping(value = "/rest/")
public class TurnosControllerREST {
	
	@Autowired
	@Qualifier("TurnosServiceTransactional")
	private TurnosService service;
	
	
	@RequestMapping(value = "turnos/alta",method=RequestMethod.POST)
    public ResponseEntity<Turno> newTurno(@RequestBody Turno turno) {
		return new ResponseEntity<Turno>(service.saveTurno(turno), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "turnos/{turnoId}",method=RequestMethod.GET,produces="application/json")
    public ResponseEntity<Turno> getTurno( @PathVariable int turnoId) {
		return new ResponseEntity<Turno>(service.getTurno(turnoId), HttpStatus.OK);
        
    }
	
	@RequestMapping(value = "turnos/cliente/{clienteId}",method=RequestMethod.GET,produces="application/json")
    public ResponseEntity<List<Turno>> getTurnosCliente( @PathVariable int clienteId) {
		return new ResponseEntity<List<Turno>>(service.getTurnosCliente(clienteId), HttpStatus.OK);
    }
	
	@RequestMapping(value = "turnos/actualizar/{turnoId}",method=RequestMethod.PUT)
    public ResponseEntity<Turno> updateTurno(@RequestBody Turno turno) {
		Turno updated = service.updateTurno(turno);
		return new ResponseEntity<Turno>(updated, HttpStatus.OK);
	}
	
	@RequestMapping(value = "turnos/{turnoId}/cobrado",method=RequestMethod.PATCH)
    public ResponseEntity<Turno> setCobrado( @PathVariable int turnoId) {
		return new ResponseEntity<Turno>(service.setCobrado(turnoId), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "turnos",method=RequestMethod.GET)
    public ResponseEntity<List<Turno>> getTurnosDelDia(@RequestParam @DateTimeFormat(pattern=DATE_FORMAT) Date fecha) {
		return new ResponseEntity<List<Turno>>(service.getTurnosDelDia(fecha), HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "turnos/eliminar/{turnoId}",method=RequestMethod.DELETE)
    public ResponseEntity<Turno> getTurnosDelDia(@PathVariable int turnoId) {
		return new ResponseEntity<>(service.deleteTurno(turnoId), HttpStatus.OK);
	}
	
	public TurnosService getService() {
		return service;
	}

	public void setService(TurnosService service) {
		this.service = service;
	}
	
}
