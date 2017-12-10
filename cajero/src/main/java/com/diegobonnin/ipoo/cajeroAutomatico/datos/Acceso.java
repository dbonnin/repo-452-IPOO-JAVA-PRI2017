package com.diegobonnin.ipoo.cajeroAutomatico.datos;

import java.time.LocalDateTime;

import com.diegobonnin.ipoo.cajeroAutomatico.Cajero;

public class Acceso {
	
	private Long id;
	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;
	private String ip;
	private Cliente cliente;
	private Cajero cajero;

	public Acceso(){
	}

	public Acceso(LocalDateTime fechaHoraInicio, Cliente cliente, Cajero cajero) {
		super();
		this.fechaHoraInicio = fechaHoraInicio;
		this.cliente = cliente;
		this.cajero=cajero;
	}

	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public LocalDateTime getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Cliente getCliente() {
		System.out.println("Acceso.getCliente().");
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cajero getCajero() {
		return cajero;
	}

	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}


}
