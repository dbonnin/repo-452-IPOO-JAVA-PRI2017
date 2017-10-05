package com.diegobonnin.ipoo.repuestos.datos;

import java.util.ArrayList;
import java.util.List;

public class Minorista extends Cliente{
	
	public Minorista(){
		super();
	}

	@Override
	public List<FormaDePago> obtenerFormasDePago() {
		List<FormaDePago> formasDePago=new ArrayList<>();
		formasDePago.add(new FormaDePago("Contado", 0, 0));
		return formasDePago;
	}
	
	
	
	

}
