package com.diegobonnin.cajeroAutomatico;

import java.util.List;

import com.diegobonnin.cajeroAutomatico.datos.Acceso;
import com.diegobonnin.cajeroAutomatico.datos.Cliente;
import com.diegobonnin.cajeroAutomatico.datos.Cuenta;

public interface SistemaBanco {

	Cliente obtCliente(String tipoDoc, String nroDoc, String password) throws SistemaBancoException;

	Acceso crearAcceso(Cliente cliente, String ip) throws SistemaBancoException;
	
	void finalizarSesion(Acceso acceso) throws SistemaBancoException;

	Acceso obtAcceso(Cliente cliente) throws SistemaBancoException;

	Cuenta obtCuenta(String nroDeCuenta) throws SistemaBancoException;

	List<Cuenta> obtCuentas(Cliente cliente) throws SistemaBancoException;
	
	

}
