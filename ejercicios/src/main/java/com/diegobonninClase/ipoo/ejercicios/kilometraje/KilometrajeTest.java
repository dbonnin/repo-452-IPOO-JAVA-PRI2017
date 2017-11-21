package com.diegobonninClase.ipoo.ejercicios.kilometraje;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class KilometrajeTest {
	
	private List<Viaje> viajes;
	private static final int CANTIDAD_VIAJES=100;
	
	@Before
	public void iniciar(){
		viajes=new ArrayList<Viaje>();
		Random r=new Random();
		
		for(int i=0; i<CANTIDAD_VIAJES; i++){
			
			Viaje v=new Viaje();
			v.setViaje("Viaje Nro. " + (i+1));
			v.setLitros(r.nextInt(100));
			v.setKilometros(r.nextInt(1000));
			
			viajes.add(v);
			
			System.out.println(v);
		}
	}
	
	@Test
	public void test(){
		
		assertTrue(viajes.size()==CANTIDAD_VIAJES);
		
		int totalKilometros=0;
		int totalLitros=0;
		
		Viaje vMaxLt=new Viaje();
		vMaxLt.setLitros(0);
		
		Viaje vMaxKm=new Viaje();
		vMaxKm.setKilometros(0);

		
		int i=0;
		while(i<viajes.size()){
			
			Viaje v=viajes.get(i);
			totalKilometros += v.getKilometros(); 
			// += equivale a totalKilometros = totalKilometros + v.getKilometros()
			totalLitros += v.getLitros();
			i++;
			
			if(vMaxLt.getLitros() < v.getLitros()){
				vMaxLt=v;
			}
			
			if(vMaxKm.getKilometros() < v.getKilometros()){
				vMaxKm=v;
			}			
			
		}
		
		System.out.println("Recorrido total: " + totalKilometros);
		System.out.println("Total litros: " + totalLitros);
		System.out.println("Promedio total (Litros por KM): " + (new Double(totalLitros)/new Double(totalKilometros)));
		System.out.println("Mayor Recorrido: " + vMaxKm);
		System.out.println("Mayor Litraje: " + vMaxLt);
		
	}

}
