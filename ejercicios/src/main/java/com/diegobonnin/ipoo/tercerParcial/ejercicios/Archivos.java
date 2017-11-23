package com.diegobonnin.ipoo.tercerParcial.ejercicios;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Archivos {
	
	
	public String leerArchivo(String archivo){

		FileReader entrada=null;
		char[] buffer=new char[1024];
		StringBuilder sb=new StringBuilder();
		
		try {
			entrada=new FileReader(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while(entrada.read(buffer, 0, 1024)>0){
					sb.append(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(entrada!=null)
			try {
				entrada.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return sb.toString();
		
	}
	
	public void escribirArchivo(String archivo, String contenido) throws IOException{

		FileWriter salida=null;
		char[] buffer=contenido.toCharArray();
		
	
		salida=new FileWriter(archivo);
		salida.write(buffer, 0, buffer.length);
			
		if(salida!=null) salida.close();
		
	}
	


}
