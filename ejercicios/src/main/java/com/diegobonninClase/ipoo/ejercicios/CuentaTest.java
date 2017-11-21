package com.diegobonninClase.ipoo.ejercicios;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class CuentaTest {
	
	@Test
	public void test(){
		
		List<Cuenta> ctas=new ArrayList<Cuenta>();
		
		int contador=1;
		
		Random rand=new Random();
		
		while(contador <= 6){
			
			System.out.println(contador);

			Cuenta c1=new Cuenta();
			if((contador % 2)==0){
				c1.setNroDocumento("99999999");
				c1.setTipoDocumento("CI");
			}else{
				c1.setNroDocumento("2902466");
				c1.setTipoDocumento("CI");
			}
			
			c1.setNroCuenta(new Integer(rand.nextInt(10000)).toString());
			c1.setTipoCuenta("CC");
			
			ctas.add(c1);
			
			contador++;
		}
		
		assertTrue(ctas.size()==6);
		
		contador=1;
		
		while(contador <= 6){

			Cuenta c1=new Cuenta();
			if((contador % 2)==0){
				c1.setNroDocumento("99999999");
				c1.setTipoDocumento("CI");
			}else{
				c1.setNroDocumento("2902466");
				c1.setTipoDocumento("CI");
			}
			
			c1.setNroCuenta(new Integer(rand.nextInt(10000)).toString());
			c1.setTipoCuenta("AH");
			
			ctas.add(c1);
			
			contador++;
		}		
		
		assertTrue(ctas.size()==12);
		
		Cliente cli=new Cliente();
		cli.setNroDocumento("2902466");
		cli.setTipoDocumento("CI");
		
		for(int i=0; i<ctas.size(); i++){
			
			Cuenta cuenta=ctas.get(i);
			
			if(
				cli.getNroDocumento().equals(cuenta.getNroDocumento())
				&&
				cli.getTipoDocumento().equals(cuenta.getTipoDocumento())
			){
				
				cli.asignarCuenta(cuenta);
				
			}
		}
		
		assertTrue(cli.getCuentasAhorro().size()==3);
		assertTrue(cli.getCuentasCorrientes().size()==3);
		
		String nroCuenta=null;
		
		for(int i=0; i<cli.getCuentasAhorro().size(); i++){
			// Obtengo el ultimo numero de cuenta
			nroCuenta=cli.getCuentasAhorro().get(i).getNroCuenta();
		}
		
		imprimirLista(cli.getCuentasAhorro());
		
		System.out.println("Nro. Cuenta Recuperado: " + nroCuenta);
		// Creamos una instancia
		Cuenta cuentaBuscada=new Cuenta();
		cuentaBuscada.setNroCuenta(nroCuenta);
		
		// Buscamos en la lista
		assertTrue(cli.getCuentasAhorro().contains(cuentaBuscada));
		
		imprimirLista(cli.getCuentasCorrientes());
		
		// 
		ctas.remove(8);
		// comprueba que ya no tiene 12
		assertFalse(ctas.size()==12);
		// comprueba que solo uno quito
		assertTrue(ctas.size()==11);
		System.out.println("Tam: " + ctas.size());
		
		imprimirLista(ctas);
		ctas.remove(cuentaBuscada);
		assertTrue(ctas.size()==10);
		System.out.println("Tam: " + ctas.size());
		imprimirLista(ctas);
		
		assertTrue(ctas.size()>0);
		ctas.clear();
		assertTrue(ctas.size()==0);
		
		imprimirLista(ctas);
		
		List<Cuenta> cuentasCliente=cli.obtenerCuentas();
		assertTrue(cuentasCliente.size()==6);
		imprimirLista(cuentasCliente);
		
	}
	
	public void imprimirLista(List<Cuenta> l){
		System.out.println("****");
		for(int i=0; i<l.size(); i++){
			System.out.println(l.get(i));
		}
		System.out.println("****");
		
	}

}
