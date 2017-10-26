package com.diegobonnin.ipoo.ejercicios.polimorfismo;

import java.time.LocalDate;

public class Cuota {
	
	private Integer numero;
	private Double interes;
	private LocalDate fecha;
	private Double acumulado;
	
	public Cuota(Integer numero, Double interes, LocalDate fecha, Double acumulado) {
		super();
		this.numero = numero;
		this.interes = interes;
		this.fecha = fecha;
		this.acumulado = acumulado;
	}

	public Integer getNumero() {
		return numero;
	}

	public Double getInteres() {
		return interes;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public Double getAcumulado() {
		return acumulado;
	}

	@Override
	public String toString() {
		return numero + "\t" + interes + "\t" + fecha + "\t" + acumulado + "\n";
	}
	
	
}
