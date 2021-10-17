package com.bolsadeideas.springboot.di.app.models.domain;

import java.util.Objects;

public class ItemFactura {
	
	//Relacion Producto
	private Producto producto;
	//Cantidad del producto
	private Integer cantidad;
	
	public ItemFactura(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cantidad, producto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ItemFactura))
			return false;
		ItemFactura other = (ItemFactura) obj;
		return Objects.equals(cantidad, other.cantidad) && Objects.equals(producto, other.producto);
	}
	@Override
	public String toString() {
		return "ItemFactura [producto=" + producto + ", cantidad=" + cantidad + "]";
	}
	
	public Integer calcularImporte() {
		return cantidad * producto.getPrecio();
	}

}
