package com.diegobonnin.ipoo.cajeroAutomatico.datos;

public class PagoTarjeta extends Transaccion{
	
	private Tarjeta tarjeta;
	
	public PagoTarjeta(){
		
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	

}
