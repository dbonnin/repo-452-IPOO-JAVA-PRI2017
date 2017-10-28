package com.diegobonnin.cajeroAutomatico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.diegobonnin.cajeroAutomatico.datos.Acceso;
import com.diegobonnin.cajeroAutomatico.datos.Cliente;
import com.diegobonnin.cajeroAutomatico.datos.Cuenta;
import com.diegobonnin.cajeroAutomatico.datos.ResultadoOperacion;
import com.diegobonnin.cajeroAutomatico.datos.Transferencia;

public class SistemaBancoDummy implements SistemaBanco {
	
	private List<Acceso> accesos;
	private List<Cliente> clientes;
	private List<Cuenta> cuentas;
	private Long semillaIds;
	
	
	public SistemaBancoDummy(){
		
		semillaIds=0l;
		
		clientes=new ArrayList<>();

		Cliente ma=new Cliente();
		ma.setId(++semillaIds);
		ma.setTipoDocumento("RUC");
		ma.setNroDocumento("80008822-8");
		ma.setNombre("Repuestos El Mecánico SRL");
		ma.setPassword("123456");
		
		clientes.add(ma);
		
		Cliente mi=new Cliente();
		mi.setId(++semillaIds);
		mi.setTipoDocumento("CI");
		mi.setNroDocumento("2902466");
		mi.setNombre("Diego Bonnin");
		mi.setPassword("123456");
		

		clientes.add(mi);
		
	}
	
	@Override
	public Cliente obtCliente(String tipoDoc, String nroDoc, 
			String password) {
		for(Cliente c: clientes){
			if(c.getTipoDocumento().equals(tipoDoc) && 
					c.getNroDocumento().equals(nroDoc) && 
					c.getPassword().equals(password)){
				return c;
			}
		}
		return null;
	}

	@Override
	public Acceso crearAcceso(Cliente cliente, String ip) {
		LocalDateTime fechaHoraInicio=LocalDateTime.now();
		Acceso a=new Acceso(fechaHoraInicio, ip, cliente);
		accesos.add(a);
		return a;
	}

	@Override
	public Acceso obtAcceso(Cliente cliente) {
		for(Acceso a: accesos){
			Cliente c=a.getCliente();
			if(c.equals(cliente)){
				return a;
			}
		}
		return null;
	}

	@Override
	public Cuenta obtCuenta(String nroDeCuenta) {
		for(Cuenta c: cuentas){
			if(c.getNroDeCuenta().equals(nroDeCuenta)){
				return c;
			}
		}
		return null;
	}

	public ResultadoOperacion registrarOperacion(Transferencia transferencia) {
		// TODO Auto-generated method stub
		return null;
	}	

}
