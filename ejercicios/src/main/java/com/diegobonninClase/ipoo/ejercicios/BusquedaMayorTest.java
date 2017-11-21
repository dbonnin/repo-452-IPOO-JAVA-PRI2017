package com.diegobonninClase.ipoo.ejercicios;

import java.util.Random;

import org.junit.Test;

public class BusquedaMayorTest {
	
	@Test
	public void test(){
		
		int[] numeros=new int[10];
		
		Random r=new Random();
		
		for(int i=0; i<numeros.length; i++){
			numeros[i]=r.nextInt(50000);
		}
		
		int mayor=0;
		
		for(int i=0; i<numeros.length; i++){
			if(mayor<numeros[i]) mayor=numeros[i];
		}
		
		System.out.println("numeros: ");
		for(int i=0; i<numeros.length; i++){
			System.out.print(numeros[i] + ", ");
		}
		System.out.println();
		System.out.println("Mayor: " + mayor);
		
		
		
	}

}
