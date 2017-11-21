package com.diegobonninClase.ipoo.ejercicios.archivos;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArchivosTest {

	@Test
	public void testCopiar() {
		
		String origen="C:\\452-IPOO-JAVA-PRI2017\\archivos\\oorigen.txt";
		String destino="C:\\452-IPOO-JAVA-PRI2017\\archivos\\destino.txt";
		
		Archivos a=new Archivos();
		a.copiar(origen, destino);
		
	}

}
