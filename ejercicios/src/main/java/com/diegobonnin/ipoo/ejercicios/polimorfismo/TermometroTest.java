package com.diegobonnin.ipoo.ejercicios.polimorfismo;

import org.junit.Test;

public class TermometroTest {
	
	@Test
	public void test(){
		
		Termometro t=new Termometro();
		
		System.out.println("C a F: " + t.convertirTemperatura(20.0d, MedidaTemperatura.FARENHEIT));
		System.out.println("F a C: " + t.convertirTemperatura(20.0d, MedidaTemperatura.CELSIUS));
		System.out.println("default: " + t.convertirTemperatura(20.0d, MedidaTemperatura.UNDEFINED));
		
	}

}
