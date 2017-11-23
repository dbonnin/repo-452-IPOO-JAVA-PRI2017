package com.diegobonnin.ipoo.tercerParcial.ejercicios;

import java.util.Random;

public class Numeros {

	private int[] numeros;
	private int semilla;

	public Numeros(int semilla){

		this.semilla=semilla;
		
		Random r=new Random();
		numeros=new int[100];

		for(int i=0; i<numeros.length; i++){
			numeros[i]=r.nextInt(semilla);
		}
		
	}
	
	public int getMenor(){
		
		int menor=semilla;
		
		for(int i=0; i<numeros.length; i++){
			
			if(numeros[i] < menor) menor=numeros[i];
			
		}
		
		return menor;
		
	}
	
	public int getMayor(){
		
		int mayor=0;
		
		for(int i=0; i<numeros.length; i++){
			
			if(numeros[i] > mayor) mayor=numeros[i];
			
		}
		
		return mayor;
		
	}
	
	public boolean buscar(int numero){
		
		int i=0;
		
		while(i<numeros.length){
			if(numeros[i]==numero) return true;
			i++;
		}
		
		return false;
		
	}
	
	public int getRepeticiones(int numero){
		// indice
		int i=0;
		// contador de repeticiones
		int repeticiones=0;
		
		// recorro el array
		while(i<numeros.length){
			// veo si el miembro actual es igual al numero que recibi
			if(numeros[i]==numero){
				// si asi, aumento la cantidad de repeticiones
				repeticiones++;
			}
			// incremento mi indice
			i++;
		}
		
		// devuelvo el resultado
		return repeticiones;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
