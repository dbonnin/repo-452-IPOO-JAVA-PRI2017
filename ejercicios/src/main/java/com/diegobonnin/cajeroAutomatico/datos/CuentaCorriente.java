package com.diegobonnin.cajeroAutomatico.datos;

public class CuentaCorriente extends Cuenta{

	@Override
	public String obtTipoCuenta() {
		return "CC";
	}

}
