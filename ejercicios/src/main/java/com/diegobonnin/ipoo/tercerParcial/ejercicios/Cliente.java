package com.diegobonnin.ipoo.tercerParcial.ejercicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
	private Long id;
	private String nombre;
	private String nroDocumento;
	private String tipoDocumento;
	private String direccion;
	private String nroTelefono;
	private String password;
	
	private List<Prestamo> prestamos;
	
	
	public Cliente(){
		prestamos=new ArrayList<Prestamo>();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(String nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nroDocumento == null) ? 0 : nroDocumento.hashCode());
		result = prime * result + ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (nroDocumento == null) {
			if (other.nroDocumento != null)
				return false;
		} else if (!nroDocumento.equals(other.nroDocumento))
			return false;
		if (tipoDocumento == null) {
			if (other.tipoDocumento != null)
				return false;
		} else if (!tipoDocumento.equals(other.tipoDocumento))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", nroDocumento=" + nroDocumento + ", tipoDocumento="
				+ tipoDocumento + ", direccion=" + direccion + ", nroTelefono=" + nroTelefono + ", password=" + password
				+ "]";
	}
	
	
	public boolean esMoroso(){
		
		boolean esMoroso=false;
		
		for(int i=0; i<prestamos.size(); i++){
			
			Prestamo p=prestamos.get(i);
			
			for(int c=0; c<p.getCuotas().size(); c++){
				
				CuotaPrestamo cp=p.getCuotas().get(c);
				
				if(cp.getFechaVencimiento().isBefore(LocalDate.now())){
					
					if(cp.getImportePagado() < cp.getImporte()){
						
						esMoroso=true;
						break;
						
					}
				}
				
			}
			
		}
		
		return esMoroso;
		
		
	}
	
	public void aplicarPago(Double importe){
		
		Double saldo=importe;
		
		for(int i=0; i<prestamos.size(); i++){
			
			Prestamo p=prestamos.get(i);
			
			for(int c=0; c<p.getCuotas().size(); c++){
				
				CuotaPrestamo cp=p.getCuotas().get(c);
				
				// si aun no se pago la cuota
				if(cp.getImportePagado() < cp.getImporte()){
					
					// Obtengo el saldo no pagado de la cuota
					double aplicacion=cp.getImporte()-cp.getImportePagado();
					
					// Quito el saldo de esta cuota del importe pagado
					saldo = saldo - aplicacion;
					
					// aplico el pago
					cp.setImportePagado(cp.getImportePagado() + aplicacion);
						
				}
				
			}
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
