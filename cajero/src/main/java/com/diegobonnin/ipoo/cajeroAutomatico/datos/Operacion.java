package com.diegobonnin.ipoo.cajeroAutomatico.datos;

import java.time.LocalDateTime;

import com.diegobonnin.ipoo.cajeroAutomatico.Cajero;

public abstract class Operacion {
	
	private Long nroOperacion;
	private LocalDateTime fechaHora;
	private byte[] imagen;
	private Acceso acceso;
	private ResultadoOperacion resultado;
	protected String tipo;
	
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
