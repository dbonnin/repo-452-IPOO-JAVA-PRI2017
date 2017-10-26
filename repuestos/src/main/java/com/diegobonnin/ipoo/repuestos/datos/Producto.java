package com.diegobonnin.ipoo.repuestos.datos;

import java.util.List;

public class Producto {
	
	private Long id;
	private String nombre;
	private String descripcion;
	private List<Modelo> modelos;
	private Double precio;
	private Proveedor proveedor;
	private Long existencia;
	
	public Producto(){
		
	}
	
	public Producto(Long id, String nombre){
		this.id=id;
		this.nombre=nombre;
	}
	
	
	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Long getExistencia() {
		return existencia;
	}

	public void setExistencia(Long existencia) {
		this.existencia = existencia;
	}

}
