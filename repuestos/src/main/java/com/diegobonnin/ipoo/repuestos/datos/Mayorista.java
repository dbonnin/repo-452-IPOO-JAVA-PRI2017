package com.diegobonnin.ipoo.repuestos.datos;

import java.util.ArrayList;
import java.util.List;

public class Mayorista extends Cliente {
	
	private List<FormaDePago> formasDePago;
	private Double lineaDeCredito;
	
	public Mayorista(Double lineaDeCredito){
		this();
		this.lineaDeCredito=lineaDeCredito;
	}
	
	public Mayorista(){
		super();
		formasDePago=new ArrayList<FormaDePago>();
		formasDePago.add(new FormaDePago("30 Días", 30, 0));
		formasDePago.add(new FormaDePago("60 Días", 60, 8));
		formasDePago.add(new FormaDePago("90 Días", 90, 12));

	}

	@Override
	public List<FormaDePago> obtenerFormasDePago() {
		return formasDePago;
	}

	public Double getLineaDeCredito() {
		return lineaDeCredito;
	}


}
