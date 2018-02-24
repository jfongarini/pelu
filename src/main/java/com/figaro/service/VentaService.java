package com.figaro.service;

import static com.figaro.util.Constants.CATEGORIA_VENTAS;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import com.figaro.dto.VentaDTO;
import com.figaro.model.Item;
import com.figaro.model.Movimiento;
import com.figaro.model.Producto;
import com.figaro.model.Venta;
import com.figaro.repository.MovimientosRepository;
import com.figaro.repository.VentaRepository;
import com.figaro.service.ProductosService;



public class VentaService {

	final static Logger LOGGER = Logger.getLogger(VentaService.class);
	
	private VentaRepository repository;
	private ProductosService productosService;
	private MovimientosRepository movimientosRepository;	
	
	public Venta getVenta(int ventaID) {
		LOGGER.debug("Obteniendo la Venta con ID: " + ventaID);
		return repository.getVenta(ventaID);
	}
	
	public Venta saveVenta (VentaDTO dto) {
		LOGGER.info("Guardando la Venta: " + dto.getVenta().toString());
		Integer newId = repository.saveVenta(dto.getVenta());
		dto.getVenta().setId(newId);
		LOGGER.info("La venta se guardó correctamente");
		actualizarStock(dto.getVenta().getItems());
		dto.getVenta().setCobroVenta(generarCobroVenta(dto));
		return dto.getVenta();
	}

	public void actualizarStock(List<Item> items) {
		LOGGER.info("Actualizando stock...");
		int newCantidad;
		Producto p;
		
		for(int i = 0; i < items.size(); i++) {
	        p = productosService.buscarDesdeVenta(items.get(i).getNombreProducto(), items.get(i).getDescripcionProducto());
	        LOGGER.info("Se va a actualizar stock del Producto: " + p.toString());
	        newCantidad = (p.getCantidad()-items.get(i).getCantidad());
	        p = productosService.updateCantidad(p.getId(), newCantidad);
		}	
	}

	private Movimiento generarCobroVenta(VentaDTO dto) {
		Movimiento movimiento = new Movimiento();
		movimiento.setCategoria(CATEGORIA_VENTAS);
		movimiento.setIsGasto(false);
		movimiento.setFecha(dto.getVenta().getFecha());
		movimiento.setPrecio(dto.getVenta().getPrecio());
		movimiento.setDetalle(dto.getVenta().getIdToString());
		movimiento.setTipoPago(dto.getMovimiento().getTipoPago());
		movimiento.setCuotas(dto.getMovimiento().getCuotas());
		movimiento.setDescuento(dto.getMovimiento().getDescuento());
		movimiento.descontar();
		return movimiento;
	}	
	
	public List<Venta> searchVentas(Date from, Date to) {		
		LOGGER.info("Ingresa a ProductoService");
		return repository.searchBetween(from,to); 
	}
	
	public Integer createItem (Item item) {	
		return repository.saveItem(item);
	}
	
	
	public Venta deleteVenta(int ventaId) {
		Venta venta = getVenta(ventaId);
		LOGGER.info("Eliminando la Venta: " + venta.toString());
		repository.deleteVenta(venta);
		return venta;
	}
	
	public List<Venta> getAllVenta(){
		LOGGER.debug("Obteniendo todas las ventas");
		return repository.getAll();
	}	
	
	public VentaRepository getRepository() {
		return repository;
	}
	
	public void setRepository(VentaRepository repository) {
		this.repository = repository;
	}

	public ProductosService getProductosService() {
		return productosService;
	}

	public void setProductosService(ProductosService productosService) {
		this.productosService = productosService;
	}

	public MovimientosRepository getMovimientosRepository() {
		return movimientosRepository;
	}

	public void setMovimientosRepository(MovimientosRepository movimientosRepository) {
		this.movimientosRepository = movimientosRepository;
	}
	
}
