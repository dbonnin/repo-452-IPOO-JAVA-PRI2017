package com.diegobonnin.ipoo.cajeroAutomatico;

import java.time.LocalDateTime;
import java.util.List;

import com.diegobonnin.ipoo.cajeroAutomatico.datos.Acceso;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Cliente;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Cuenta;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.MovimientoCuenta;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Operacion;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.ResultadoOperacion;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Transferencia;

public class Cajero {
	
	private Long id;
	private String nombre;
	private String direccion;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public SistemaBancoJDBC getBanco() {
		return banco;
	}

	public void setBanco(SistemaBancoJDBC banco) {
		this.banco = banco;
	}

	private SistemaBancoJDBC banco;
	
	public Cajero(){
		banco=new SistemaBancoJDBC();
		this.id=1l;
		this.nombre="Cajero Palma";
		this.direccion="Palma 555 casi Alberdi";
	}
	
	public Cajero(Long id) {
		this.id=id;
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
		Cuenta cuentaDestino=null;
		
		try {
			cuentaOrigen = banco.obtCuenta(nroCuentaOrigen);
		} catch (SistemaBancoException e) {
			e.printStackTrace();
		}
		
		
		if(cuentaOrigen!=null){
			
			transferencia.setMoneda(cuentaOrigen.getMoneda());
			
			cuentaDestino=null;
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
						estado="ERROR";
						mensaje="Saldo insuficiente";
					}
					
				}else{
					estado="ERROR";
					mensaje="Cuentas con moneda distinta";
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
		System.out.println(ro);
		
		if("OK".equals(transferencia.getResultado().getEstado())){
			
			registrarMovimiento(cuentaOrigen, importe, acceso, transferencia, "D");
			cuentaOrigen.setSaldoDisponible(cuentaOrigen.getSaldoDisponible() - importe);
			try {
				banco.actualizarSaldoCuenta(cuentaOrigen);
			} catch (SistemaBancoException e) {
				e.printStackTrace();
			}
			
			registrarMovimiento(cuentaDestino, importe, acceso, transferencia, "C");
			cuentaDestino.setSaldoDisponible(cuentaDestino.getSaldoDisponible() + importe);
			try {
				banco.actualizarSaldoCuenta(cuentaDestino);
			} catch (SistemaBancoException e) {
				e.printStackTrace();
			}
			
		}
		
		return transferencia;
		
		
	}
	
	private void registrarMovimiento(Cuenta cuenta, Double importe, Acceso acceso, Operacion operacion, String sentido){
		
		MovimientoCuenta mc=new MovimientoCuenta();
		mc.setCuenta(cuenta);
		mc.setImporte(importe);
		mc.setDescripcion("Operación en ATM - " + acceso.getCajero().getId()
		+ " - " + acceso.getCajero().getNombre()
		+ " - " + acceso.getCajero().getDireccion());
		
		mc.setFechaHora(LocalDateTime.now());
		mc.setOperacion(operacion);
		mc.setSentido(sentido);
		
		try {
			banco.registrarMovimiento(mc);
		} catch (SistemaBancoException e) {
			e.printStackTrace();
		}
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
