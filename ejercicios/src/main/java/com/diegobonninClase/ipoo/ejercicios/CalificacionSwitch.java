package com.diegobonninClase.ipoo.ejercicios;

public class CalificacionSwitch {
	
	public CalificacionSwitch(){
	}

	public String calificar(int calificacion){
		
		switch(calificacion){
			case 5:
				return "Excelente";
			case 4:
				return "Muy Bueno";
			case 3:
				return "Bueno";
			case 2:
				return "Aceptable";
			case 1:
				return "Insuficiente";
			default:
				return "Calificación inválida";
		}
		
	}

}
