package com.diegobonninClase.ipoo.ejercicios.archivos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Archivos {
	
	public String leerArchivo(String archivo){
		
		StringBuilder sb=new StringBuilder();
		char[] buffer=new char[1024];
		
		FileReader entrada=null;
		
		try{
			
			entrada=new FileReader(archivo);
			while(entrada.read(buffer, 0, 1024) > 0){
				sb.append(buffer);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(entrada!=null)
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		
		return sb.toString();
		
		
	}
	
	public void escribirArchivo(String archivo, String contenido){
		
		char[] buffer=contenido.toCharArray();
		FileWriter salida=null;
		
		try{
			
			salida=new FileWriter(archivo);
			salida.write(buffer, 0, buffer.length);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(salida!=null)
				try {
					salida.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
	

}
