package com.diegobonnin.ipoo.repuestos.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.diegobonnin.ipoo.repuestos.Repuestos;
import com.diegobonnin.ipoo.repuestos.datos.Acceso;
import com.diegobonnin.ipoo.repuestos.datos.Marca;
import com.diegobonnin.ipoo.repuestos.datos.Modelo;
import com.diegobonnin.ipoo.repuestos.datos.Producto;

public class RepuestosTest {
	
	@Test
	public void test(){
		
		Repuestos r=new Repuestos();
		
		Acceso a=r.autenticar("CI", "2902466", "123456", "127.0.0.1");
		assertNotNull(a);
		assertNotNull(a.getCliente());
		
		List<Producto> productos=r.obtProductos();
		assertNotNull(productos);
		assertFalse(productos.isEmpty());
		
		List<Marca> marcas=r.obtMarcas();
		assertNotNull(marcas);
		assertFalse(marcas.isEmpty());
		
		for(Marca m: marcas){
			List<Modelo> modelos=r.obtModelos(m);
			assertNotNull(modelos);
			assertFalse(modelos.isEmpty());
		}
		
	}

}
