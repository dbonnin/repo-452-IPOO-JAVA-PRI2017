package com.diegobonnin.cajeroAutomatico.datos;

public class Cliente {
	
	private Long id;
	private String tipoDoc;
	private String nroDoc;
	private String password;
	private String nombre;
	
	public Cliente(){
		
	}
	
	public Cliente(Long id, String tipoDoc, String nroDoc, String password, String nombre) {
		super();
		this.id = id;
		this.tipoDoc = tipoDoc;
		this.nroDoc = nroDoc;
		this.password = password;
		this.nombre = nombre;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNroDoc() {
		return nroDoc;
	}
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}
