package com.diegobonnin.cajeroAutomatico.datos;

import java.time.LocalDateTime;

import com.diegobonnin.cajeroAutomatico.Cajero;

public abstract class Operacion {
	
	private Long nroOperacion;
	private LocalDateTime fechaHora;
	private byte[] imagen;
	private Cajero cajero;
	private Acceso acceso;
	private ResultadoOperacion resultado;
	
	public Operacion(){
		
	}
	
	public Long getNroOperacion() {
		return nroOperacion;
	}
	public void setNroOperacion(Long nroOperacion) {
		this.nroOperacion = nroOperacion;
	}
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Cajero getCajero() {
		return cajero;
	}

	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}

	public Acceso getAcceso() {
		return acceso;
	}

	public void setAcceso(Acceso acceso) {
		this.acceso = acceso;
	}

	public ResultadoOperacion getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoOperacion resultado) {
		this.resultado = resultado;
	}
	
	

}
