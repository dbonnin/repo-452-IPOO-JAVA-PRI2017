package com.diegobonnin.ipoo.ejercicios.polimorfismo;

import org.junit.Test;

public class CalculoCuotaTest {
	
	@Test
	public void test(){
		
		System.out.println("Interés Simple");
		CalculoAhorro c1=new InteresSimple(5, 100000.0d, 0.05);
		System.out.println(c1.calcular());

		System.out.println("Interés Compuesto");
		CalculoAhorro c2=new InteresCompuesto(5, 100000.0d, 0.05);
		System.out.println(c2.calcular());
		
	}

}
