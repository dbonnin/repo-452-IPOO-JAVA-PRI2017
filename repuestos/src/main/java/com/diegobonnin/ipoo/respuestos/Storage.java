package com.diegobonnin.ipoo.respuestos;

import com.diegobonnin.ipoo.repuestos.datos.Acceso;
import com.diegobonnin.ipoo.repuestos.datos.Cliente;

public interface Storage {
	
	Cliente obtCliente(Long id);
	Cliente obtCliente(String nroDoc, String tipoDoc, String password);
	Acceso crearAcceso(Cliente cliente, String ip);
	Acceso obtAcceso(Cliente cliente);
	
	
}
