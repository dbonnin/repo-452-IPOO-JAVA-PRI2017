package com.diegobonninClase.ipoo.ejercicios.archivos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Persona {
	
	private String tipoDoc;
	private String nroDoc;
	private String nombre;
	private String fechaNacimiento;
	
	public Persona(){
		
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public int getEdad(){
		
		DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		LocalDate d=LocalDate.parse(fechaNacimiento, formatter);
		LocalDate hoy=LocalDate.now();
		
		return hoy.getYear() - d.getYear();
		
	}

	@Override
	public String toString() {
		return "Persona [tipoDoc=" + tipoDoc + ", nroDoc=" + nroDoc + ", nombre=" + nombre + ", fechaNacimiento="
				+ fechaNacimiento + "]";
	}

}
