package com.diegobonnin.cajeroAutomatico;


import com.diegobonnin.cajeroAutomatico.datos.Cliente;
import com.diegobonnin.cajeroAutomatico.datos.Cuenta;
import com.diegobonnin.cajeroAutomatico.datos.CuentaCorriente;
import com.diegobonnin.cajeroAutomatico.datos.CuentaDeAhorro;
import com.diegobonnin.cajeroAutomatico.datos.Prestamo;
import com.diegobonnin.cajeroAutomatico.datos.Tarjeta;

public class Main {
	
	public static void main(String[] args){
		
		/*
		System.out.println("Test Cajero");
		
		Cliente c=new Cliente();
		c.setNombre("Diego Bonnin");
		
		// Crear una instancia de prestamo
		Prestamo p=new Prestamo();
		p.setNroPrestamo("123456");
		p.setCliente(c);
		
		System.out.println("Nro. Prestamo: " + p.getNroPrestamo());
		System.out.println("Cliente Prestamo: " + p.getCliente().getNombre());
		
		Tarjeta t=new Tarjeta();
		t.setNroTarjeta("123456486789");
		t.setCliente(c);
		
		System.out.println("Nro. Tarjeta: " + t.getNroTarjeta());
		System.out.println("Cliente Tarjeta: " + t.getCliente().getNombre());
		
		System.out.println("**********");

		Cuenta cta=new CuentaDeAhorro();
		cta.setNroDeCuenta("999999");
		System.out.println("Nro. Cuenta: " + cta.getNroDeCuenta());
		System.out.println("Tipo Cuenta: " + cta.obtTipoCuenta());
		
		Cuenta ctaCte=new CuentaCorriente();
		ctaCte.setNroDeCuenta("666666");
		System.out.println("Nro. Cuenta: " + ctaCte.getNroDeCuenta());
		System.out.println("Tipo Cuenta: " + ctaCte.obtTipoCuenta());
		
		Cuenta cuenta=null;
		
		System.out.println("**********");
		cuenta=cta;
		System.out.println("Tipo Cuenta en <cuenta>: " + cuenta.obtTipoCuenta());
		cuenta=ctaCte;
		System.out.println("Tipo Cuenta en <cuenta>: " + cuenta.obtTipoCuenta());
		
		*/
		
		Cliente c1=new Cliente();
		c1.setId(1l);
		c1.setNroDocumento("2902466");
		c1.setTipoDocumento("CI");
		c1.setNombre("Diego");
		Cliente c2=new Cliente();
		c2.setId(2l);
		c2.setNombre("Jesus");
		c2.setNroDocumento("2902466");
		c2.setTipoDocumento("RUC");

		
		System.out.println("c1.equals(c2): " + c1.equals(c2));
		
		
		System.out.println("Cliente c1: " + c1);
		System.out.println("Cliente c2: " + c2);
		
		
		
		
		
		
		
		
		
		
	}

}
