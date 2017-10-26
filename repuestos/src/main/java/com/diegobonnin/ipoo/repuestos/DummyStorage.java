package com.diegobonnin.ipoo.repuestos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.diegobonnin.ipoo.repuestos.datos.Acceso;
import com.diegobonnin.ipoo.repuestos.datos.Cliente;
import com.diegobonnin.ipoo.repuestos.datos.EstadoPedido;
import com.diegobonnin.ipoo.repuestos.datos.Marca;
import com.diegobonnin.ipoo.repuestos.datos.Mayorista;
import com.diegobonnin.ipoo.repuestos.datos.Minorista;
import com.diegobonnin.ipoo.repuestos.datos.Modelo;
import com.diegobonnin.ipoo.repuestos.datos.Pedido;
import com.diegobonnin.ipoo.repuestos.datos.Producto;
import com.diegobonnin.ipoo.repuestos.datos.ProductoCompuesto;

public class DummyStorage implements Storage{
	
	private List<Acceso> accesos;
	private List<Cliente> clientes;
	private Long semillaIds;
	private List<Producto> productos;	
	private List<Marca> marcas;
	private List<Modelo> modelos;
	
	public DummyStorage(){
		
		semillaIds=0l;
		
		clientes=new ArrayList<>();

		Mayorista ma=new Mayorista(10000000.00d);
		ma.setId(++semillaIds);
		ma.setTipoDocumento("RUC");
		ma.setNroDocumento("80008822-8");
		ma.setNombre("Repuestos El Mecánico SRL");
		ma.setPassword("123456");
		
		clientes.add(ma);
		
		Minorista mi=new Minorista();
		mi.setId(++semillaIds);
		mi.setTipoDocumento("CI");
		mi.setNroDocumento("2902466");
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
		
		Marca toyota=new Marca(++semillaIds, "Toyota", "Marca japonesa de vehiculos");
		Modelo corrolla=new Modelo(++semillaIds, "Corolla", toyota);
		Modelo rav4=new Modelo(++semillaIds, "RAV4", toyota);
		Modelo hilux=new Modelo(++semillaIds, "hilux", toyota);
		
		marcas.add(new Marca(++semillaIds, "Audi", "Marca alemana de vehiculos deportivos"));
		marcas.add(new Marca(++semillaIds, "Ford", "Marca americana de vehiculos"));
		marcas.add(new Marca(++semillaIds, "Peugeot", "Marca francesa de vehiculos"));
		
		
		
		
	}
	

	@Override
	public Producto obtProducto(Long id) {
		
		for(Producto p: productos){
			if(p.getId().equals(id)){
				return p;
			}
		}
		
		return null;
		
		
	}


	@Override
	public List<Producto> obtProductos() {
		return productos;
	}


	@Override
	public List<Producto> obtProductos(List<Marca> marcas, List<Modelo> modelos) {
		
		List<Producto> resultado=new ArrayList<Producto>();
		
		if(marcas!=null && !marcas.isEmpty()){
		
			for(Producto p: productos){
				
				for(Marca m: marcas){
					
					for(Modelo mo: p.getModelos()){
						
						if(mo.getMarca().getId().equals(m.getId())){
							
							resultado.add(p);
							
						}
					}
					
				}
				
			}
			
		}
		
		if(modelos!=null && !modelos.isEmpty()){
			
			for(Producto p: productos){
				
				for(Modelo m: modelos){
					
					for(Modelo mo: p.getModelos()){
						
						if(mo.getId().equals(m.getId())){
							
							resultado.add(p);
							
						}
					}
					
				}
				
			}
			
		}		
		
		return resultado;	
	
	}


	@Override
	public List<Producto> obtProductos(String nombre) {
		
		List<Producto> resultado=new ArrayList<>();
		
		for(Producto p: productos){
			if(p.getNombre().contains(nombre)){
				resultado.add(p);
			}
		}
		
		return resultado;	
	
	}


	@Override
	public Marca obtMarca(Long id) {
		
		for(Marca m: marcas){
			if(m.getId().equals(id)){
				return m;
			}
		}
		
		return null;
		

	}


	@Override
	public Modelo obtModelo(Long id) {
		for(Modelo m: modelos){
			if(m.getId().equals(id)){
				return m;
			}
		}
		
		return null;
	}


	@Override
	public List<Marca> obtMarcas() {
		return marcas;
	}


	@Override
	public List<Modelo> obtModelos(Marca marca) {
		
		List<Modelo> resultado=new ArrayList<>();
		
		for(Modelo m: modelos){
			if(m.getMarca().getId().equals(marca.getId())){
				resultado.add(m);
			}
		}
		
		return resultado;	
		
	}


	@Override
	public Pedido crearPedido(Pedido pedido) {
		return null;
	}


	@Override
	public Pedido actualizarPedido(Pedido pedido) {
		return null;
	}


	@Override
	public List<Pedido> obtPedidos(Cliente cliente, List<EstadoPedido> estados) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Pedido obtPedido(Long id) {
		// TODO Auto-generated method stub
		return null;
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
			if(c.getTipoDocumento().equals(tipoDoc) && c.getNroDocumento().equals(nroDoc) && c.getPassword().equals(password)){
				// si coincide
				return c;
			}
		}
		return null;
	}

	@Override
	public Acceso crearAcceso(Cliente cliente, String ip) {
		LocalDateTime fechaHoraInicio=LocalDateTime.now();
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
