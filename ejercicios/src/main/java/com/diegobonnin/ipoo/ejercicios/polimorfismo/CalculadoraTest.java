package com.diegobonnin.ipoo.ejercicios.polimorfismo;

import org.junit.Test;

public class CalculadoraTest {
	
	@Test
	public void test(){
		
		System.out.println("Calculadora sin decimales");
		Calculadora c1=new CalculadoraNumerosSinDecimales();
		_test(c1);
		
		System.out.println("Calculadora con decimales");
		Calculadora c2=new CalculadoraNumerosConDecimales();
		_test(c2);
		
	}
	
	public void _test(Calculadora c){
		
		Double sumando1=10.00d;
		Double sumando2=3.00d;
		
		System.out.println(c.sumar(sumando1, sumando2));
		
		Double dividendo=10.00d;
		Double divisor=3.00d;
		
		System.out.println(c.dividir(dividendo, divisor));
		
	}

}
