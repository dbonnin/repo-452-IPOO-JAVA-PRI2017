package com.diegobonnin.ipoo.repuestos.datos;

public class DetalleProducto {
	
	public Producto producto;
	public Long cantidad;
	public Double precio;
	
	public DetalleProducto(Producto producto, Long cantidad, Double precio) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public Producto getProducto() {
		return producto;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public Double getPrecio() {
		return precio;
	}
	
	public void setCantidad(Long cantidad){
		this.cantidad=cantidad;
	}
	
	

}
