package com.diegobonnin.ipoo.repuestos;

import java.util.List;

import com.diegobonnin.ipoo.repuestos.datos.Acceso;
import com.diegobonnin.ipoo.repuestos.datos.Cliente;
import com.diegobonnin.ipoo.repuestos.datos.EstadoPedido;
import com.diegobonnin.ipoo.repuestos.datos.Marca;
import com.diegobonnin.ipoo.repuestos.datos.Modelo;
import com.diegobonnin.ipoo.repuestos.datos.Pedido;
import com.diegobonnin.ipoo.repuestos.datos.Producto;

public interface Storage {
	
	Cliente obtCliente(Long id);
	Cliente obtCliente(String nroDoc, String tipoDoc, String password);
	Acceso crearAcceso(Cliente cliente, String ip);
	Acceso obtAcceso(Cliente cliente);
	Producto obtProducto(Long id);
	List<Producto> obtProductos();
	List<Producto> obtProductos(List<Marca> marcas, List<Modelo> modelos);
	List<Producto> obtProductos(String nombre);
	Marca obtMarca(Long id);
	Modelo obtModelo(Long id);
	List<Marca> obtMarcas();
	List<Modelo> obtModelos(Marca marca);
	Pedido crearPedido(Pedido pedido);
	Pedido actualizarPedido(Pedido pedido);
	List<Pedido> obtPedidos(Cliente cliente, List<EstadoPedido> estados);
	Pedido obtPedido(Long id);
	
	
}
