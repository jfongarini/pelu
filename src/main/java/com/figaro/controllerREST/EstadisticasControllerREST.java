package com.figaro.controllerREST;



import static com.figaro.util.Constants.DATE_FORMAT;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.figaro.model.Movimiento;
import com.figaro.service.EstadisticasService;

@RestController
@RequestMapping(value = "/rest/")
public class EstadisticasControllerREST {
	
	@Autowired
	@Qualifier("EstadisticasServiceTransactional")
	private EstadisticasService service;
	
	@RequestMapping(value = "estadisticas/clientesCiudad",method=RequestMethod.GET,produces="application/json")
    public ResponseEntity<Map<String, Integer>> buscarClienteCiudad() {
        return new ResponseEntity<Map<String, Integer>>(service.buscarClienteCiudad(), HttpStatus.OK);
    }
	
	@RequestMapping(value = "estadisticas/clientesSexo",method=RequestMethod.GET,produces="application/json")
    public ResponseEntity<Map<String, Integer>> buscarClienteSexo() {
        return new ResponseEntity<Map<String, Integer>>(service.buscarClienteSexo(), HttpStatus.OK);
    }
	
	@RequestMapping(value = "estadisticas/productoMasVendido",method=RequestMethod.GET,produces="application/json")
    public ResponseEntity<Map<String, Integer>> buscarProductoMasVendido(@RequestParam  @DateTimeFormat(pattern=DATE_FORMAT) Date from, @RequestParam @DateTimeFormat(pattern=DATE_FORMAT) Date to) throws ParseException {		
        return new ResponseEntity<Map<String, Integer>>(service.buscarProductoMasVendido(from,to), HttpStatus.OK);
    }
		
	public EstadisticasService getService() {
		return service;
	}
	
	public void setService(EstadisticasService service) {
		this.service = service;
	}
}