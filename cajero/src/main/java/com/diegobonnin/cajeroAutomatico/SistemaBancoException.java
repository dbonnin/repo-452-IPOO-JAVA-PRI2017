package com.diegobonnin.cajeroAutomatico;
public class SistemaBancoException extends Exception {
	
	public SistemaBancoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SistemaBancoException(String message, Throwable cause) {
		super(message, cause);
	}

	public SistemaBancoException(Throwable cause) {
		super(cause);
	}

	public SistemaBancoException(){
		super();
	}
	
	public SistemaBancoException(String message){
		super(message);
	}

}