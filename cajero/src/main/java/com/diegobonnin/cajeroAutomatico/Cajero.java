package com.diegobonnin.cajeroAutomatico;

import java.time.LocalDateTime;

import com.diegobonnin.cajeroAutomatico.datos.Acceso;
import com.diegobonnin.cajeroAutomatico.datos.Cliente;
import com.diegobonnin.cajeroAutomatico.datos.Cuenta;
import com.diegobonnin.cajeroAutomatico.datos.ResultadoOperacion;
import com.diegobonnin.cajeroAutomatico.datos.Transferencia;

public class Cajero {
	
	private SistemaBancoDummy banco;
	
	public Acceso autenticar(String tipoDoc, String nroDoc, String password, String ip) {
		
		Cliente c=banco.obtCliente(tipoDoc, nroDoc, password);
		
		if(c!=null){
			Acceso a=banco.crearAcceso(c, ip);
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
		transferencia.setCajero(this);
		
		Cuenta cuentaOrigen=banco.obtCuenta(nroCuentaOrigen);
		
		
		if(cuentaOrigen!=null){
			
			transferencia.setMoneda(cuentaOrigen.getMoneda());
			
			Cuenta cuentaDestino=banco.obtCuenta(nroCuentaDestino);
			
			if(cuentaDestino!=null){
				
				if(cuentaOrigen.getMoneda().equals(cuentaDestino.getMoneda())){
					
					if(cuentaOrigen.getSaldoDisponible() >= transferencia.getImporte()){
						
						transferencia.setCuenta(cuentaOrigen);
						transferencia.setCuentaDestino(cuentaDestino);
						ro=banco.registrarOperacion(transferencia);
						
					}else{
						
					}
					
				}
				
			}
			
		}
		
		if(ro==null){
			ro=new ResultadoOperacion(estado, mensaje);
		}
		
		transferencia.setResultado(ro);
		
		return transferencia;
		
		
	}

}
