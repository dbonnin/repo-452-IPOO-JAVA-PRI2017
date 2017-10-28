package com.diegobonnin.cajeroAutomatico;

import com.diegobonnin.cajeroAutomatico.datos.Acceso;
import com.diegobonnin.cajeroAutomatico.datos.Cliente;
import com.diegobonnin.cajeroAutomatico.datos.Cuenta;

public interface SistemaBanco {

	Cliente obtCliente(String tipoDoc, String nroDoc, String password);

	Acceso crearAcceso(Cliente cliente, String ip);

	Acceso obtAcceso(Cliente cliente);

	Cuenta obtCuenta(String nroDeCuenta);

}
