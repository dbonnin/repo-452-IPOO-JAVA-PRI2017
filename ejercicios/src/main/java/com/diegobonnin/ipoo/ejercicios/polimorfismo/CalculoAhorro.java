package com.diegobonnin.ipoo.ejercicios.polimorfismo;

import java.util.ArrayList;
import java.util.List;

public abstract class CalculoAhorro {
	
	protected int plazo;
	protected Double capital;
	protected Double tasa;
	protected List<Cuota> cuotas;
	

	public CalculoAhorro(int plazo, Double capital, Double tasa) {
		super();
		this.plazo = plazo;
		this.capital = capital;
		this.tasa = tasa;
		cuotas=new ArrayList<>();
	}
	
	public int getPlazo() {
		return plazo;
	}

	public Double getCapital() {
		return capital;
	}

	public Double getTasa() {
		return tasa;
	}

	
	public abstract List<Cuota> calcular();


}
