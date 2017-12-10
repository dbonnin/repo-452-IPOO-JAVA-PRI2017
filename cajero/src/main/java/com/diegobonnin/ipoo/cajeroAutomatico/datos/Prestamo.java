package com.diegobonnin.ipoo.cajeroAutomatico.datos;

public class Prestamo implements Producto {
	
	private String nroPrestamo;
	private Double capital;
	private Integer plazo;
	private Double saldo;
	private Moneda moneda;
	private CuotaPrestamo proximaCuotaAVencer;
	private Cliente cliente;

	public Prestamo() {
		
	}
	
	

	public Prestamo(String nroPrestamo, Double capital, Integer plazo, Double saldo, Moneda moneda) {
		super();
		this.nroPrestamo = nroPrestamo;
		this.capital = capital;
		this.plazo = plazo;
		this.saldo = saldo;
		this.moneda = moneda;
	}



	public String getNroPrestamo() {
		return nroPrestamo;
	}

	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}

	public Double getCapital() {
		return capital;
	}

	public void setCapital(Double capital) {
		this.capital = capital;
	}

	public Integer getPlazo() {
		return plazo;
	}

	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}



	public CuotaPrestamo getProximaCuotaAVencer() {
		return proximaCuotaAVencer;
	}



	public void setProximaCuotaAVencer(CuotaPrestamo proximaCuotaAVencer) {
		this.proximaCuotaAVencer = proximaCuotaAVencer;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public String obtResumentSaldo() {
		return 	nroPrestamo + " - " + capital + " - " + plazo + " - " + saldo;
	}
	
	

}
