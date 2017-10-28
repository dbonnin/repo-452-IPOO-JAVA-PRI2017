package com.diegobonninClase.ipoo.ejercicios;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CalificacionTest {
	
	@Test
	public void test(){
		
		Calificacion c=new Calificacion();
		String esperado="Excelente";
		String resultado=null;
		// primitivo
		// int calificacion=95;
		Integer calificacion=95;
		
		resultado=c.calificar(calificacion);
		// comproba que esta variable no este nula
		assertNotNull(resultado);
		
		// comproba que es verdadero
		assertTrue(resultado.equals(esperado));
		
		
		esperado="Muy Bueno";
		resultado=null;
		calificacion=85;
		
		resultado=c.calificar(calificacion);
		assertNotNull(resultado);
		
		// comproba que es verdadero
		assertTrue(resultado.equals(esperado));
		
	}
	
	@Test
	public void testSwitch(){
		
		Calificacion c=new Calificacion();
		String esperado="Excelente";
		String resultado=null;
		int calificacion=5;
		
		resultado=c.calificar(calificacion);
		// comproba que esta variable no este nula
		assertNotNull(resultado);
		
		// comproba que es verdadero
		assertTrue(resultado.equals(esperado));
		
		
		esperado="Muy Bueno";
		resultado=null;
		calificacion=5;
		
		resultado=c.calificar(calificacion);
		assertNotNull(resultado);
		
		// comproba que es verdadero
		assertTrue(resultado.equals(esperado));
		
	}
	

}
