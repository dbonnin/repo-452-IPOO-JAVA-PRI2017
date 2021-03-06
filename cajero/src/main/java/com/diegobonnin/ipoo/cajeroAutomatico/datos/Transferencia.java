package com.diegobonnin.ipoo.cajeroAutomatico.datos;

public class Transferencia extends Transaccion {
	
	private Cuenta cuenta;
	private Cuenta cuentaDestino;
	
	public Transferencia(){
		tipo="TRANSFERENCIA";
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	@Override
	public String toString() {
		return "Transferencia [cuenta=" + cuenta + ", cuentaDestino=" + cuentaDestino + ", tipo=" + tipo
				+ ", getResultado()=" + getResultado() + "]";
	}

}
