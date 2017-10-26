package com.diegobonnin.ipoo.repuestos.datos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
	
	private Long id;
	private Acceso acceso;
	private Cliente cliente;
	private Date fechaHora;
	private FormaDePago formaDePago;
	private EstadoPedido estado;
	
	private List<DetalleProducto> productos;
	
	public Pedido(Cliente cliente, Acceso acceso, Date fechaHora) {
		this.cliente = cliente;
		this.fechaHora = fechaHora;
		productos=new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}


	public Date getFechaHora() {
		return fechaHora;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public List<DetalleProducto> getProductos() {
		return productos;
	}
	
	public void agregarProducto(Producto producto) {
		productos.add(new DetalleProducto(producto, 1l, producto.getPrecio()));
	}	

	public void agregarProducto(Producto producto, Long cantidad) {
		productos.add(new DetalleProducto(producto, cantidad, producto.getPrecio()));
	}
	
	public void quitarProducto(Producto producto){
		for(DetalleProducto dp: productos){
			if(dp.getProducto().equals(producto.getId())){
				productos.remove(dp);
				break;
			}
		}
	}
	
	public void quitarProducto(Producto producto, Long cantidad){
		for(DetalleProducto dp: productos){
			if(dp.getProducto().equals(producto.getId())){
				if(dp.getCantidad() > cantidad){
					dp.setCantidad(dp.getCantidad() - cantidad);
				}else{
					this.quitarProducto(producto);
				}
				break;
			}
		}
	}	
	
	public Double obtenerTotal(FormaDePago formaDePago){
		Double total=0.0d;
		for(DetalleProducto p: productos){
			total+=p.getPrecio();
		}
		return (total - (total*formaDePago.getPorcentajeRecargo()/100));
	}
	
	

}
