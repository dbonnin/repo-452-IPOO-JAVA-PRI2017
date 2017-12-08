package com.diegobonnin.cajeroAutomatico.datos;

import java.time.LocalDateTime;

public class Acceso {
	
	private Long id;
	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;
	private String ip;
	private Cliente cliente;

	public Acceso(){
	}

	public Acceso(LocalDateTime fechaHoraInicio, String ip, Cliente cliente) {
		super();
		this.fechaHoraInicio = fechaHoraInicio;
		this.ip = ip;
		this.cliente = cliente;
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


}
