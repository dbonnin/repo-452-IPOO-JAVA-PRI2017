package com.diegobonnin.cajeroAutomatico.datos;

public class BeneficiarioTarjeta {
	
	private String nroTarjeta;
	private Double topeDisponible;
	private String nombreBeneficiario;
	private Tarjeta tarjeta;
	
	public BeneficiarioTarjeta(){
		
	}

	public BeneficiarioTarjeta(String nroTarjeta, Double topeDisponible, String nombreBeneficiario, Tarjeta tarjeta) {
		super();
		this.nroTarjeta = nroTarjeta;
		this.topeDisponible = topeDisponible;
		this.nombreBeneficiario = nombreBeneficiario;
		this.tarjeta = tarjeta;
	}

	public String getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public Double getTopeDisponible() {
		return topeDisponible;
	}

	public void setTopeDisponible(Double topeDisponible) {
		this.topeDisponible = topeDisponible;
	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}


}
