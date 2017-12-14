package com.diegobonnin.ipoo.cajeroAutomatico.datos;

public abstract class Transaccion extends Operacion {
	
	protected Double importe;
	protected Moneda moneda;
	
	public Transaccion(){
		
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

}
