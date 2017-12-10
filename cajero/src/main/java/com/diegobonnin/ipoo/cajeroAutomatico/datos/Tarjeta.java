package com.diegobonnin.ipoo.cajeroAutomatico.datos;

import java.time.LocalDate;

public class Tarjeta implements Producto{
	
	private String nroTarjeta;
	private Double lineaDeCredito;
	private Double saldoActual;
	private Double saldoDisponible;
	private LocalDate fechaVencimiento;
	private Double pagoMinimo;
	private Cliente cliente;
	private Moneda moneda;
	
	public Tarjeta(){
		
	}
	
	public Tarjeta(String nroTarjeta, Double lineaDeCredito, Double saldoActual, Double saldoDisponible,
			LocalDate fechaVencimiento, Double pagoMinimo) {
		super();
		this.nroTarjeta = nroTarjeta;
		this.lineaDeCredito = lineaDeCredito;
		this.saldoActual = saldoActual;
		this.saldoDisponible = saldoDisponible;
		this.fechaVencimiento = fechaVencimiento;
		this.pagoMinimo = pagoMinimo;
	}

	public String getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public Double getLineaDeCredito() {
		return lineaDeCredito;
	}

	public void setLineaDeCredito(Double lineaDeCredito) {
		this.lineaDeCredito = lineaDeCredito;
	}

	public Double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(Double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public Double getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(Double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Double getPagoMinimo() {
		return pagoMinimo;
	}

	public void setPagoMinimo(Double pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public String obtResumentSaldo() {
		return 	nroTarjeta + " - " + lineaDeCredito + " - " + saldoActual + " - " + saldoDisponible + " - " + fechaVencimiento + " - " + pagoMinimo;
	}
	
	
	
	

}
