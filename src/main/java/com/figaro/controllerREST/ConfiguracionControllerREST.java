package com.figaro.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.figaro.model.Categoria;
import com.figaro.model.Ciudad;
import com.figaro.model.Peluquero;
import com.figaro.model.Trabajo;
import com.figaro.service.ConfiguracionService;

@RestController
@RequestMapping(value = "/rest/configuracion")
public class ConfiguracionControllerREST {

	@Autowired
	@Qualifier("ConfiguracionServiceTransactional")
	private ConfiguracionService service;
	
	@RequestMapping(value = "/ciudades",method=RequestMethod.GET)
    public ResponseEntity<List<Ciudad>> getCiudades() {
		return new ResponseEntity<List<Ciudad>>(service.getCiudades(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ciudades/alta",method=RequestMethod.POST)
    public ResponseEntity<Ciudad> newCiudad(@RequestBody Ciudad ciudad) {
		Integer newID = service.saveCiudad(ciudad);
		ciudad.setId(newID);
		return new ResponseEntity<Ciudad>(ciudad, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/ciudades/baja/{idCiudad}",method=RequestMethod.DELETE)
    public ResponseEntity<Ciudad> deleteCiudad(@PathVariable Integer idCiudad) {
		service.deleteCiudad(idCiudad);
		return new ResponseEntity<Ciudad>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/trabajos",method=RequestMethod.GET)
    public ResponseEntity<List<Trabajo>> getTrabajos() {
		return new ResponseEntity<List<Trabajo>>(service.getTrabajos(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/trabajos/alta",method=RequestMethod.POST)
    public ResponseEntity<Trabajo> addTrabajo(@RequestBody Trabajo trabajo) {
		Integer newID = service.saveTrabajo(trabajo);
		trabajo.setId(newID);
		return new ResponseEntity<Trabajo>(trabajo, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/trabajos/baja/{idTrabajo}",method=RequestMethod.DELETE)
    public ResponseEntity<Trabajo> deleteTrabajo(@PathVariable Integer idTrabajo) {
		service.deleteTrabajo(idTrabajo);
		return new ResponseEntity<Trabajo>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/trabajos/actualizar/{idTrabajo}",method=RequestMethod.PUT)
    public ResponseEntity<Trabajo> updateTrabajo(@RequestBody Trabajo trabajo) {
		Trabajo updated = service.updateTrabajo(trabajo);
		return new ResponseEntity<Trabajo>(updated ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/trabajos/{idTrabajo}",method=RequestMethod.GET)
    public ResponseEntity<Trabajo> getTrabajo(@PathVariable Integer idTrabajo) {
		Trabajo trabajo = service.getTrabajo(idTrabajo);
		return new ResponseEntity<Trabajo>(trabajo ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/trabajos/buscar",method=RequestMethod.GET,produces="application/json")
    public ResponseEntity<List<Trabajo>> searchTrabajo(@RequestParam String search) {
        return new ResponseEntity<List<Trabajo>>(service.buscarTrabajos(search), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/peluqueros",method=RequestMethod.GET)
    public ResponseEntity<List<Peluquero>> getPeluqueros() {
		return new ResponseEntity<List<Peluquero>>(service.getPeluqueros(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/peluqueros/alta",method=RequestMethod.POST)
    public ResponseEntity<Peluquero> addPeluquero(@RequestBody Peluquero peluquero) {
		Integer newID = service.savePeluquero(peluquero);
		peluquero.setId(newID);
		return new ResponseEntity<Peluquero>(peluquero, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/peluqueros/baja/{idPeluquero}",method=RequestMethod.DELETE)
    public ResponseEntity<Peluquero> deletePeluquero(@PathVariable Integer idPeluquero) {
		service.deletePeluquero(idPeluquero);
		return new ResponseEntity<Peluquero>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/categorias",method=RequestMethod.GET)
    public ResponseEntity<List<Categoria>> getCategorias() {
		return new ResponseEntity<List<Categoria>>(service.getCategorias(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/categorias/alta",method=RequestMethod.POST)
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria) {
		Integer newID = service.save(categoria);
		categoria.setId(newID);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/categorias/baja/{idCategoria}",method=RequestMethod.DELETE)
    public ResponseEntity<Categoria> deleteCategoria(@PathVariable Integer idCategoria) {
		service.deleteCategoria(idCategoria);
		return new ResponseEntity<Categoria>(HttpStatus.OK);
	}
	
}
