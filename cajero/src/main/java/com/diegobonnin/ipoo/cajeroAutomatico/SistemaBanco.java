package com.diegobonnin.ipoo.cajeroAutomatico;

import java.util.List;

import com.diegobonnin.ipoo.cajeroAutomatico.datos.Acceso;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Cliente;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Cuenta;

public interface SistemaBanco {

	Cliente obtCliente(String tipoDoc, String nroDoc, String password) throws SistemaBancoException;

	Acceso crearAcceso(Cliente cliente, Cajero cajero) throws SistemaBancoException;
	
	void finalizarSesion(Acceso acceso) throws SistemaBancoException;

	Acceso obtAcceso(Cliente cliente) throws SistemaBancoException;

	Cuenta obtCuenta(String nroDeCuenta) throws SistemaBancoException;

	List<Cuenta> obtCuentas(Cliente cliente) throws SistemaBancoException;
	
	

}
