package com.diegobonnin.ipoo.respuestos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.diegobonnin.ipoo.repuestos.datos.Acceso;
import com.diegobonnin.ipoo.repuestos.datos.Cliente;
import com.diegobonnin.ipoo.repuestos.datos.Mayorista;
import com.diegobonnin.ipoo.repuestos.datos.Minorista;
import com.diegobonnin.ipoo.repuestos.datos.Producto;
import com.diegobonnin.ipoo.repuestos.datos.ProductoCompuesto;

public class DummyStorage implements Storage{
	
	private List<Acceso> accesos;
	private List<Cliente> clientes;
	private Long semillaIds;
	private List<Producto> productos;	
	
	public DummyStorage(){
		
		
		semillaIds=0l;
		
		clientes=new ArrayList<>();

		Mayorista ma=new Mayorista(10000000.00d);
		ma.setId(++semillaIds);
		ma.setTipoDoc("RUC");
		ma.setNroDoc("80008822-8");
		ma.setNombre("Repuestos El Mecánico SRL");
		ma.setPassword("123456");
		
		clientes.add(ma);
		
		Minorista mi=new Minorista();
		mi.setId(++semillaIds);
		mi.setTipoDoc("CI");
		mi.setNroDoc("2902466");
		mi.setNombre("Diego Bonnin");
		mi.setPassword("123456");

		clientes.add(mi);
		
		productos=new ArrayList<>();
		
		Producto p1=new Producto();
		
		p1.setId(++semillaIds);
		p1.setNombre("Tornillo");
		p1.setPrecio(1000.0d);
		p1.setExistencia(1000l);
		
		productos.add(p1);
		
		Producto p2=new Producto();
		
		p2.setId(++semillaIds);
		p2.setNombre("Bomba de Agua");
		p2.setPrecio(120000.0d);
		p2.setExistencia(100l);
		
		productos.add(p2);
		
		Producto p3=new Producto();
		
		p3.setId(++semillaIds);
		p3.setNombre("Junta");
		p3.setPrecio(80000.0d);
		p3.setExistencia(250l);
		
		productos.add(p3);
		
		ProductoCompuesto p4=new ProductoCompuesto();
		
		p4.setId(++semillaIds);
		p4.setNombre("Motor Diesel");
		p4.setPrecio(5000000.0d);
		p4.setExistencia(10l);
		
		p4.agregarParte(p1);
		p4.agregarParte(p3);
		p4.agregarParte(p4);
		
		productos.add(p4);
		
		accesos=new ArrayList<>();
		
		
	}
	

	@Override
	public Cliente obtCliente(Long id) {
		// por cada instancia de cliente en la lista "clientes"
		for(Cliente c: clientes){
			// comparo el id recibido con el que posee la instancia
			if(c.getId().equals(id)){
				// si coincide
				return c;
			}
		}
		return null;
	}
		

	@Override
	public Cliente obtCliente(String tipoDoc, String nroDoc, String password) {
		// por cada instancia de cliente en la lista "clientes"
		for(Cliente c: clientes){
			System.out.println("Recorriendo: " + tipoDoc + nroDoc + password);
			// comparo el tipo doc, nro doc y password recibido con el que posee la instancia
			if(c.getTipoDoc().equals(tipoDoc) && c.getNroDoc().equals(nroDoc) && c.getPassword().equals(password)){
				// si coincide
				return c;
			}
		}
		return null;
	}

	@Override
	public Acceso crearAcceso(Cliente cliente, String ip) {
		Date fechaHoraInicio=new Date();
		Acceso a=new Acceso(fechaHoraInicio, ip, cliente);
		accesos.add(a);
		return a;
	}

	@Override
	public Acceso obtAcceso(Cliente cliente) {
		System.out.println("Cliente buscado: " + cliente.getId());
		// por cada acceso en accesos
		for(Acceso a: accesos){
			// obtengo el cliente que registro el acceso
			Cliente c=a.getCliente();
			System.out.println("Comparando con cliente: " + a.getCliente().getId());
			// veo si es el cliente que se recibio por parametro
			if(c.getId().equals(cliente.getId())){
				// si corresponde, retorno
				return a;
			}
		}
		return null;
	}


}
