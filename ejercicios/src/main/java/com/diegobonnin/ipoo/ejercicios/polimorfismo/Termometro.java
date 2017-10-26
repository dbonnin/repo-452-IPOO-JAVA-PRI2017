package com.diegobonnin.ipoo.ejercicios.polimorfismo;

public class Termometro {
	
	public Termometro() {
	}
	
	public Double convertirTemperatura(Double temperatura, MedidaTemperatura medida){
		switch(medida){
			case CELSIUS:
				return new Double((temperatura - 32) * 5/9);
			case FARENHEIT:
				return new Double((temperatura*9/5)+32);
			default:
				return temperatura;
		}
	}

}
