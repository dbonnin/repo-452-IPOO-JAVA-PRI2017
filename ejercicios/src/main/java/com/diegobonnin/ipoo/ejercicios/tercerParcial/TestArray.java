package com.diegobonnin.ipoo.ejercicios.tercerParcial;

import java.util.Random;

import org.junit.Test;

public class TestArray {
	
	@Test
	public void test(){
		
		Random r=new Random();
		int[] nums=new int[100];
		
		for(int i=0; i<nums.length; i++){
			nums[i]=r.nextInt(100000);
		}
		
		int menor=100000;
		int mayor=0;
		
		int contador=0;
		while(contador < nums.length){
			if(menor > nums[contador]) menor=nums[contador];
			if(mayor < nums[contador]) mayor=nums[contador];
			contador++;
		}
		
		System.out.println("Menor es: " + menor);
		System.out.println("Mayor es: " + mayor);
		
		
	}

}
