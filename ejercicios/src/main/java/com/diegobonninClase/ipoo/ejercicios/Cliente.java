package com.diegobonninClase.ipoo.ejercicios;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
	private String nroDocumento;
	private String tipoDocumento;
	private List<Cuenta> cuentasAhorro;
	private List<Cuenta> cuentasCorrientes;
	
	public Cliente(){
		cuentasAhorro=new ArrayList<Cuenta>();
		cuentasCorrientes=new ArrayList<Cuenta>();
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<Cuenta> getCuentasAhorro() {
		return cuentasAhorro;
	}

	public void setCuentasAhorro(List<Cuenta> cuentasAhorro) {
		this.cuentasAhorro = cuentasAhorro;
	}

	public List<Cuenta> getCuentasCorrientes() {
		return cuentasCorrientes;
	}

	public void setCuentasCorrientes(List<Cuenta> cuentasCorrientes) {
		this.cuentasCorrientes = cuentasCorrientes;
	}
	
	public void asignarCuenta(Cuenta cuenta){
		if("AH".equals(cuenta.getTipoCuenta())){
			System.out.println("Asignando AH");
			cuentasAhorro.add(cuenta);
		}else if("CC".equals(cuenta.getTipoCuenta())){
			System.out.println("Asignando CC");
			cuentasCorrientes.add(cuenta);
		}
	}
	
	public List<Cuenta> obtenerCuentas(){
		List<Cuenta> todas=new ArrayList<Cuenta>();
		todas.addAll(cuentasAhorro);
		todas.addAll(cuentasCorrientes);
		return todas;
	}
	
	

}
