package com.diegobonninClase.ipoo.ejercicios.archivos;

import org.junit.Before;
import org.junit.Test;

public class ArchivosTestAnterior {
	
	private String base="C:\\archivos";
	private ArchivosAnterior a;
	private ConversorObjetos co;
	
	@Before
	public void iniciar(){
		a=new ArchivosAnterior();
		co=new ConversorObjetos();
	}
	
	
	// @Test
	public void testObjeto(){
		
		Cuenta c=new Cuenta();
		String json;
		
		json = co.convertirAJson(c);
		
		a.escribirArchivo(base + "\\persona.txt",  json);		
		System.out.println(a.leerArchivo(base + "\\persona.txt"));
		
	}		
	
	// @Test
	public void testCopiar(){
		
		ArchivosAnterior a=new ArchivosAnterior();
		a.copiar(
				base + "\\origen.txt", 
				base + "\\destino.txt"
		);
		
	}
	
	@Test
	public void testSplit(){
		
		String entrada=a.leerArchivo(base + "\\cuentas.txt");
		
		String[] lineas=entrada.split("\n");
		
		for(int i=0; i<lineas.length; i++){
			
			System.out.print(lineas[i]);
			
			String[] columnas=lineas[i].split("\\|");
			
			for(int c=0; c<columnas.length; c++){
			
				System.out.print(columnas[c] + " ");
				
			}
			
			System.out.println();
			
		}
		
	}		
	


}
