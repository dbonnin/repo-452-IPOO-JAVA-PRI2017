package com.diegobonnin.ipoo.repuestos.datos;

import java.util.ArrayList;
import java.util.List;

public class ProductoCompuesto extends Producto{
	
	private List<Producto> partes;
	private static final Integer DESCUENTO=10;
	
	public ProductoCompuesto(){
		super();
		partes=new ArrayList<>();
	}
	
	@Override
	public Double getPrecio(){
		
		Double precio=super.getPrecio();
		
		for(Producto p: partes){
			precio+=p.getPrecio();
		}
		
		return (precio*DESCUENTO)/100;
		
	}
	
	public void agregarParte(Producto p){
		partes.add(p);
	}

	public List<Producto> getPartes() {
		return partes;
	}

	public void setPartes(List<Producto> partes) {
		this.partes = partes;
	}

}
