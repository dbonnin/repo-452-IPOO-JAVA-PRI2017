package com.diegobonninClase.ipoo.ejercicios;

public class Calificacion {
	
	public Calificacion(){
	}

	public String calificar(int calificacion){
		
		if(calificacion >= 90){
			return "Excelente";
		}else if(calificacion >= 80){
			return "Muy Bueno";
		}else if(calificacion >= 70){
			return "Bueno";
		}else if(calificacion >= 60){
			return "Aceptable";
		}else{
			return "Insuficiente";
		}
		
	}

}
