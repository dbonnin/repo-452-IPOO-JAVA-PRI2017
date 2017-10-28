package com.diegobonnin.cajeroAutomatico.datos;

import java.util.List;

public abstract class Cuenta implements Producto{
	
	private String nroDeCuenta;
	private Moneda moneda;
	private String denominacion;
	private Double saldoDisponible;
	private Double saldoAConfirmar;
	private List<Cliente> clientes;
	
	public Cuenta(){
		
	}
	
	public Cuenta(String nroDeCuenta, Moneda moneda, String denominacion, Double saldoDisponible,
			Double saldoAConfirmar, List<Cliente> clientes) {
		super();
		this.nroDeCuenta = nroDeCuenta;
		this.moneda = moneda;
		this.denominacion = denominacion;
		this.saldoDisponible = saldoDisponible;
		this.saldoAConfirmar = saldoAConfirmar;
		this.clientes = clientes;
	}
	
	public String getNroDeCuenta() {
		return nroDeCuenta;
	}
	public void setNroDeCuenta(String nroDeCuenta) {
		this.nroDeCuenta = nroDeCuenta;
	}
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public Double getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(Double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	public Double getSaldoAConfirmar() {
		return saldoAConfirmar;
	}
	public void setSaldoAConfirmar(Double saldoAConfirmar) {
		this.saldoAConfirmar = saldoAConfirmar;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String obtResumentSaldo() {
		return nroDeCuenta + " - " + denominacion + " - " + saldoDisponible + " - " + saldoAConfirmar;
	}
	
	public abstract String obtTipoCuenta();


}
