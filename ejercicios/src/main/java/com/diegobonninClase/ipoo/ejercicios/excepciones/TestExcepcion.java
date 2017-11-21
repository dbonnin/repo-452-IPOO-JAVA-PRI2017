package com.diegobonninClase.ipoo.ejercicios.excepciones;

import org.junit.Test;

public class TestExcepcion {
	
	
	@Test
	public void testCatch(){
		
		String s=null;
		
		try{
			s.charAt(10);
		}catch(NullPointerException nfe){
			System.out.println("s es nulo");
		}
		
		s="Prueba de excepción";
		
		try{
			s.charAt(10);
			System.out.println("s no es nulo");
		}catch(NullPointerException nfe){
			System.out.println("s es nulo");
		}

		String[] arrayS=new String[10];
		
		try{
			s=arrayS[12];
		}catch(ArrayIndexOutOfBoundsException nfe){
			System.out.println("no existe elemento 12");
		}
		
		try{
			s=arrayS[8];
			System.out.println("existe elemento 8");
		}catch(ArrayIndexOutOfBoundsException nfe){
			System.out.println("no existe elemento 12");
		}
		
				
		
		
	}

}
