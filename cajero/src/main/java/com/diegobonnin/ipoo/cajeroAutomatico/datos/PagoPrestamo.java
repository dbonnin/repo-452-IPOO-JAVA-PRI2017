package com.diegobonnin.ipoo.cajeroAutomatico.datos;

public class PagoPrestamo extends Transaccion{
	
	private Prestamo prestamo;
	
	public PagoPrestamo(){
		
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

}
