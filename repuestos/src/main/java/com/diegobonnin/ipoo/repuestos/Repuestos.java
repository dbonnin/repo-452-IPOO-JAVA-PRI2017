package com.diegobonnin.ipoo.repuestos;

import java.util.List;

import com.diegobonnin.ipoo.repuestos.datos.Acceso;
import com.diegobonnin.ipoo.repuestos.datos.Cliente;
import com.diegobonnin.ipoo.repuestos.datos.Marca;
import com.diegobonnin.ipoo.repuestos.datos.Modelo;
import com.diegobonnin.ipoo.repuestos.datos.Pedido;
import com.diegobonnin.ipoo.repuestos.datos.Producto;



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
	
	public List<Producto> obtProductos(List<Marca> marcas, List<Modelo> modelos){
		return storage.obtProductos(marcas, modelos);
	}
	
	public List<Producto> obtProductos(String nombre){
		return storage.obtProductos(nombre);
	}
	
	public List<Producto> obtProductos(){
		return storage.obtProductos();
	}
	
	public Pedido agregarProducto(Pedido pedido, Producto producto, Long cantidad){
		Producto _p=storage.obtProducto(producto.getId());
		if(_p!=null){
			if(_p.getExistencia()<=cantidad){
				pedido.agregarProducto(producto, cantidad);
				storage.actualizarPedido(pedido);
			}
		}
		return pedido;
	}
	
	public Pedido quitarProducto(Pedido pedido, Producto producto, Long cantidad){
		pedido.quitarProducto(producto, cantidad);
		storage.actualizarPedido(pedido);
		return pedido;
	}	
	
	public List<Marca> obtMarcas(){
		return storage.obtMarcas();
	}
	
	public List<Modelo> obtModelos(Marca marca){
		return storage.obtModelos(marca);
	}




}
