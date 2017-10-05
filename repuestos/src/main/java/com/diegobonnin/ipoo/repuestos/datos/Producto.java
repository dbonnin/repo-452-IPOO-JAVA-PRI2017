package com.diegobonnin.ipoo.repuestos.datos;

import java.util.List;

public class Producto {
	
	private Long id;
	private String nombre;
	private String descripcion;
	private String modelo;
	private List<Marca> marca;
	private Double precio;
	private Proveedor proveedor;
	private Long existencia;
	
	public Producto(){
		
	}
	
	public Producto(Long id, String nombre){
		this.id=id;
		this.nombre=nombre;
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

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<Marca> getMarca() {
		return marca;
	}

	public void setMarca(List<Marca> marca) {
		this.marca = marca;
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
