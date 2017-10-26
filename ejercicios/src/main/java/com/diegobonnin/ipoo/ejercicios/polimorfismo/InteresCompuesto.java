package com.diegobonnin.ipoo.ejercicios.polimorfismo;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class InteresCompuesto extends CalculoAhorro {

	public InteresCompuesto(int plazo, Double capital, Double tasa) {
		super(plazo, capital, tasa);
	}

	@Override
	public List<Cuota> calcular() {
		
		LocalDate inicio=LocalDate.now();
		
		int i=0;
		double capitalAnterior=capital;
		while(plazo > i){
			
			Double interes=capitalAnterior*tasa/12;
			Double acumulado=capitalAnterior+interes;
			LocalDate fecha=inicio.plus(Period.ofDays(30));
			inicio=fecha;
					
			cuotas.add(new Cuota(++i, interes, fecha, acumulado));
			
			capitalAnterior=capitalAnterior+interes;
			
		}
		
		
		return cuotas;
		
	}

}
