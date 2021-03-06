package com.diegobonnin.ipoo.cajeroAutomatico.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.diegobonnin.ipoo.cajeroAutomatico.Cajero;
import com.diegobonnin.ipoo.cajeroAutomatico.SistemaBancoException;
import com.diegobonnin.ipoo.cajeroAutomatico.SistemaBancoJDBC;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Acceso;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Cliente;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Cuenta;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.MovimientoCuenta;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.PagoTarjeta;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.ResultadoOperacion;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Tarjeta;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Transferencia;

public class SistemaBancoJDBCTest {

	@Test
	public void test() {
		SistemaBancoJDBC banco=new SistemaBancoJDBC();
		try {
			Cliente c=banco.obtCliente("CI", "2902466", "123456");
			assertNotNull(c);
			Acceso a=banco.crearAcceso(c, new Cajero(1l));
			System.out.println(a);
			assertNotNull(a);
			List<Cuenta> l=banco.obtCuentas(c);
			assertFalse(l.isEmpty());
			Transferencia t=new Transferencia();
			t.setCuenta(l.get(0));
			t.setCuentaDestino(l.get(1));
			t.setAcceso(a);
			t.setFechaHora(LocalDateTime.now());
			t.setImporte(500.0d);
			t.setMoneda(l.get(0).getMoneda());
			ResultadoOperacion ro=banco.registrarOperacion(t);
			assertNotNull(ro);
			System.out.println(ro);
			
			MovimientoCuenta mc=new MovimientoCuenta();
			mc.setCuenta(t.getCuenta());
			mc.setImporte(t.getImporte());
			mc.setDescripcion("Operación en ATM - " + a.getCajero().getId()
			+ " - " + a.getCajero().getNombre()
			+ " - " + a.getCajero().getDireccion());
			
			mc.setFechaHora(LocalDateTime.now());
			mc.setOperacion(t);
			mc.setSentido("D");
			
			assertTrue(banco.registrarMovimiento(mc));
			
			List<Tarjeta> tarjetas=banco.obtTarjetas(c);
			assertFalse(tarjetas.isEmpty());
			
			PagoTarjeta pc=new PagoTarjeta();
			pc.setAcceso(a);
			pc.setTarjeta(tarjetas.get(0));
			pc.setImporte(55000.0d);
			pc.setFechaHora(LocalDateTime.now());
			pc.setTipo("PAGO_TARJETA");
			pc.setMoneda(tarjetas.get(0).getMoneda());
			
			ResultadoOperacion rot=banco.registrarOperacion(pc);
			assertNotNull(rot);
			assertTrue("OK".equals(rot.getEstado()));
			
			Tarjeta tar=tarjetas.get(0);
			tar.setSaldoActual(tar.getSaldoActual() - pc.getImporte());
			tar.setSaldoDisponible(tar.getSaldoDisponible() + pc.getImporte());
			banco.actualizarSaldoTarjeta(tar);
			
		} catch (SistemaBancoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
