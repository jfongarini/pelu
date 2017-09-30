package com.figaro.model;

public class Producto {
	private int id;
	private String nombre;
	private String descripcion;
	private int cantidad;
	private int cantidadMinima;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}	

	public int getCantidadMinima() {
		return cantidadMinima;
	}
	public void setCantidadMinima(int cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Producto actualizar(Producto producto) {
		setNombre(producto.getNombre());
		setDescripcion(producto.getDescripcion());
		setCantidad(producto.getCantidad());
		setCantidadMinima(producto.getCantidadMinima());
		return this;
	}
	
}