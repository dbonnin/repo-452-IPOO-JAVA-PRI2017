package com.diegobonnin.ipoo.respuestos;

import com.diegobonnin.ipoo.repuestos.datos.Acceso;
import com.diegobonnin.ipoo.repuestos.datos.Cliente;

public class Repuestos {
	
	private Storage storage;
	
	public Repuestos(){
		storage=new DummyStorage();
	}

	public Acceso autenticar(String tipoDoc, String nroDoc, String password, String ip) {
		
		Cliente c=storage.obtCliente(tipoDoc, nroDoc, password);
		
		if(c!=null){
			Acceso a=storage.crearAcceso(c, ip);
			return a;
		}
		
		return null;
		
	}



}
