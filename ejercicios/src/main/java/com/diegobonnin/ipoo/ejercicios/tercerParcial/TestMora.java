package com.diegobonnin.ipoo.ejercicios.tercerParcial;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.diegobonnin.cajeroAutomatico.datos.CuotaPrestamo;
import com.diegobonnin.cajeroAutomatico.datos.Prestamo;

public class TestMora {
	
	private List<Prestamo> prestamos;
	
	@Before
	public void iniciar(){
		prestamos=new ArrayList<Prestamo>();
	}
	
	@Test
	public void test(){
		
		LocalDate hoy=LocalDate.now();
		
		for(int i=0; i<prestamos.size(); i++){
			Prestamo p=prestamos.get(i);
			for(int c=0; c<p.getCuotas().size(); c++){
				CuotaPrestamo cuota=p.getCuotas().get(c);
				
				if(cuota.getFechaVencimiento().isAfter(hoy)){
					
					p.getCliente().setMoroso(true);
					break;
					
				}
			}
		}
		
	}

}
