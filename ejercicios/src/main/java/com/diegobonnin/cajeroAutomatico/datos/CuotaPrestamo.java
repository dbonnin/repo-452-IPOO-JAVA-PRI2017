package com.diegobonnin.cajeroAutomatico.datos;

import java.time.LocalDate;

public class CuotaPrestamo {
	
	private Integer nroCuota;
	private LocalDate fechaVencimiento;
	private Double importe;
	private Prestamo prestamo;
	
	public CuotaPrestamo(){
		
	}
	
	public CuotaPrestamo(Integer nroCuota, LocalDate fechaVencimiento, Double importe, Prestamo prestamo) {
		super();
		this.nroCuota = nroCuota;
		this.fechaVencimiento = fechaVencimiento;
		this.importe = importe;
		this.prestamo=prestamo;
	}
	
	
	public Integer getNroCuota() {
		return nroCuota;
	}
	public void setNroCuota(Integer nroCuota) {
		this.nroCuota = nroCuota;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	

}
