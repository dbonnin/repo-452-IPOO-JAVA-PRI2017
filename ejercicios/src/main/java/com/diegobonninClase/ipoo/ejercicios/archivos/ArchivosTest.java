package com.diegobonninClase.ipoo.ejercicios.archivos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ArchivosTest {
	
	private String base="C:\\archivos";
	
	@Test
	public void testLeer(){
		
		Archivos a=new Archivos();
		String contenido=a.leerArchivo(base + "\\personas.txt");
		
		// System.out.println("Contenido del archivo: " + contenido);
		
		String lineas[]=contenido.split("\\n");
		
		List<Persona> l=new ArrayList<Persona>();
		
		// Carga
		for(int i=0; i<lineas.length; i++){
			
			String columnas[]=lineas[i].trim().split("\\|");
			
			Persona p=new Persona();
			p.setTipoDoc(columnas[0].trim());
			p.setNroDoc(columnas[1].trim());
			p.setNombre(columnas[2].trim());
			p.setFechaNacimiento(columnas[3].trim());
				
			l.add(p);
						
		}
		
		// Descargar
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<l.size(); i++){
			Persona p=l.get(i);
			sb.append(p.getTipoDoc() + "|");
			sb.append(p.getNroDoc() + "|");
			sb.append(p.getNombre() + "|");
			sb.append(p.getFechaNacimiento() + "|");
			sb.append(p.getEdad() + "|");
			sb.append(System.getProperty("line.separator"));
		}
		
		a.escribirArchivo(base + "\\nuevoPersonas.txt", sb.toString());
		
		
	}

}
