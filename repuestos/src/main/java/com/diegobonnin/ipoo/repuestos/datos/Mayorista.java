package com.diegobonnin.ipoo.repuestos.datos;

public class Mayorista extends Cliente{
	
	private Double lineaDeCredito;

	public Mayorista(Double lineaDeCredito) {
		this.lineaDeCredito=lineaDeCredito;
	}

	public Double getLineaDeCredito() {
		return lineaDeCredito;
	}

	public void setLineaDeCredito(Double lineaDeCredito) {
		this.lineaDeCredito = lineaDeCredito;
	}

}
