package com.diegobonninClase.ipoo.ejercicios.archivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Archivos {
	
	public void copiar(String origen, String destino){
		
		FileInputStream entrada=null;
		FileOutputStream salida=null;
		
		try{
			
			entrada=new FileInputStream(origen);
			salida=new FileOutputStream(destino);
			
			int b=0;
			while((b=entrada.read())!=-1){
				salida.write(b);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
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
			if(salida!=null)
				try {
					salida.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		
	}

}
