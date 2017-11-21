package com.diegobonninClase.ipoo.ejercicios.kilometraje;

public class Viaje {
	
	private String viaje;
	private int kilometros;
	private int litros;
	
	public Viaje(){
		
	}

	public String getViaje() {
		return viaje;
	}

	public void setViaje(String viaje) {
		this.viaje = viaje;
	}

	public int getKilometros() {
		return kilometros;
	}

	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	public int getLitros() {
		return litros;
	}

	public void setLitros(int litros) {
		this.litros = litros;
	}

	@Override
	public String toString() {
		return "Viaje [viaje=" + viaje + ", kilometros=" + kilometros + ", litros=" + litros + "]";
	}
	
	

}
