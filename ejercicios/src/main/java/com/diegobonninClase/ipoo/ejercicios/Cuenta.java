package com.diegobonninClase.ipoo.ejercicios;

public class Cuenta {
	
	private String nroCuenta;
	private String nroDocumento;
	private String tipoDocumento;
	private String tipoCuenta;
	
	public Cuenta(){
		
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
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

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	@Override
	public String toString() {
		return "Cuenta [nroCuenta=" + nroCuenta + ", nroDocumento=" + nroDocumento + ", tipoDocumento=" + tipoDocumento
				+ ", tipoCuenta=" + tipoCuenta + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nroCuenta == null) ? 0 : nroCuenta.hashCode());
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
		Cuenta other = (Cuenta) obj;
		if (nroCuenta == null) {
			if (other.nroCuenta != null)
				return false;
		} else if (!nroCuenta.equals(other.nroCuenta))
			return false;
		return true;
	}
	
	

}
