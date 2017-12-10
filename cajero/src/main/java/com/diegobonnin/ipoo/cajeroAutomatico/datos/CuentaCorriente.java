package com.diegobonnin.ipoo.cajeroAutomatico.datos;

public class CuentaCorriente extends Cuenta{

	@Override
	public String getTipo() {
		return "CC";
	}

}
