package com.diegobonnin.ipoo.cajeroAutomatico;

import java.time.LocalDateTime;
import java.util.List;

import com.diegobonnin.ipoo.cajeroAutomatico.datos.Acceso;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Cliente;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Cuenta;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Operacion;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.ResultadoOperacion;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Transferencia;

public class Cajero {
	
	private Long id;
	
	private SistemaBancoJDBC banco;
	
	public Cajero(){
		banco=new SistemaBancoJDBC();
		this.id=1l;
	}
	
	public List<Cuenta> obtCuentas(Cliente cliente){
		try {
			return banco.obtCuentas(cliente);
		} catch (SistemaBancoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Acceso autenticar(String tipoDoc, String nroDoc, String password, String ip) {
		
		Cliente c=null;
		try {
			c = banco.obtCliente(tipoDoc, nroDoc, password);
		} catch (SistemaBancoException e) {
			e.printStackTrace();
		}
		
		if(c!=null){
			Acceso a=null;
			try {
				a = banco.crearAcceso(c, this);
			} catch (SistemaBancoException e) {
				e.printStackTrace();
			}
			return a;
		}
		
		return null;
		
	}
	
	public Transferencia transferencia(Acceso acceso, String nroCuentaOrigen, String nroCuentaDestino, Double importe){
		
		Transferencia transferencia=new Transferencia();
		
		ResultadoOperacion ro=null;
		
		String estado=null;
		String mensaje=null;
		
		transferencia.setAcceso(acceso);
		transferencia.setImporte(importe);
		transferencia.setFechaHora(LocalDateTime.now());
		
		Cuenta cuentaOrigen=null;
		try {
			cuentaOrigen = banco.obtCuenta(nroCuentaOrigen);
		} catch (SistemaBancoException e) {
			e.printStackTrace();
		}
		
		
		if(cuentaOrigen!=null){
			
			transferencia.setMoneda(cuentaOrigen.getMoneda());
			
			Cuenta cuentaDestino=null;
			try {
				cuentaDestino = banco.obtCuenta(nroCuentaDestino);
			} catch (SistemaBancoException e) {
				e.printStackTrace();
			}
			
			if(cuentaDestino!=null){
				
				if(cuentaOrigen.getMoneda().equals(cuentaDestino.getMoneda())){
					
					if(cuentaOrigen.getSaldoDisponible() >= transferencia.getImporte()){
						
						transferencia.setCuenta(cuentaOrigen);
						transferencia.setCuentaDestino(cuentaDestino);
						
						try {
							ro=banco.registrarOperacion(transferencia);
						} catch (SistemaBancoException e) {
							e.printStackTrace();
						}
						
					}else{
						
					}
					
				}
				
			}else{
				estado="ERROR";
				mensaje="Cuenta destino inexistente";
			}
			
		}else{
			estado="ERROR";
			mensaje="Cuenta origen inexistente";
		}
		
		if(ro==null){
			ro=new ResultadoOperacion(estado, mensaje);
		}
		
		transferencia.setResultado(ro);
		
		return transferencia;
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void finalizarSesion(Acceso a) {
		
	}

	public Operacion obtOperacion(Long nroOperacion) {
		try {
			return banco.obtOperacion(nroOperacion);
		} catch (SistemaBancoException e) {
			e.printStackTrace();
		}
		return null;
	}

}
