package com.diegobonnin.ipoo.cajeroAutomatico.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.diegobonnin.ipoo.cajeroAutomatico.Cajero;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Acceso;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Cuenta;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Operacion;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Transferencia;

public class CajeroTest {
	
	@Test
	public void test(){
		
		Cajero c=new Cajero();
		
		Acceso a=c.autenticar("CI", "2902466", "123456", "127.0.0.1");
		List<Cuenta> l=c.obtCuentas(a.getCliente());
		assertFalse(l.isEmpty());
		assertNotNull(l.get(0).getNroDeCuenta());
		System.out.println(l.get(0));
		System.out.println(l.get(1));
		assertTrue(l.get(1).getMoneda().equals(l.get(1).getMoneda()));
		
		Transferencia t=c.transferencia(a, l.get(0).getNroDeCuenta(), l.get(1).getNroDeCuenta(), 1000.0d);
		System.out.println(t);
		assertNotNull(t);
		assertNotNull(t.getResultado().getEstado());
		assertNotNull(t.getNroOperacion());
		Operacion o=c.obtOperacion(t.getNroOperacion());
		assertNotNull(o);
		System.out.println(o);
		

		
		
	}

}
