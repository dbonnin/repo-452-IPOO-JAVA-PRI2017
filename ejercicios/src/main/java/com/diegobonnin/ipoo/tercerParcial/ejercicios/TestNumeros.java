package com.diegobonnin.ipoo.tercerParcial.ejercicios;

import org.junit.Test;

public class TestNumeros {
	
	@Test
	public void test(){
		
		Numeros n=new Numeros(10);
		System.out.println("Menor: " + n.getMenor());
		System.out.println("Mayor: " + n.getMayor());
		System.out.println("Buscar " + n.buscar(1500));
		System.out.println("Buscar " + n.buscar(n.getMenor()));
		System.out.println("Repeticiones " + n.getRepeticiones(n.getMenor()));
		System.out.println("Repeticiones " + n.getRepeticiones(1500));
		
	}

}
