package com.diegobonnin.ipoo.repuestos.datos;

public class FormaDePago {
	
	private String nombre;
	private Integer plazo;
	private Integer porcentajeRecargo;
	
	public FormaDePago(String nombre, Integer plazo, Integer porcentajeRecargo) {
		super();
		this.nombre = nombre;
		this.plazo = plazo;
		this.porcentajeRecargo = porcentajeRecargo;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getPlazo() {
		return plazo;
	}

	public Integer getPorcentajeRecargo() {
		return porcentajeRecargo;
	}
	
	
	
	

}
