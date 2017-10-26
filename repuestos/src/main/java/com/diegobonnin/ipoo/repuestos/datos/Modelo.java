package com.diegobonnin.ipoo.repuestos.datos;


public class Modelo {
	
	private Long id;
	private String nombre;
	private Marca marca;
	
	public Modelo(){
		
	}


	public Modelo(Long id, String nombre, Marca marca) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	
	

}
