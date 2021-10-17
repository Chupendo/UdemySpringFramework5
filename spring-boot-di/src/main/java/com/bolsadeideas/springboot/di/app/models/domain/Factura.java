package com.bolsadeideas.springboot.di.app.models.domain;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Factura {
	@Value("factura.descripcion")
	private String descripcion;
	// Relaciona la Factura con el cliente (Relación Cliente)
	// Tipo relacion >> <Cliente>:<Factura> --> 1:N Un cliente puede tener muchas
	// Facturas
	@Autowired // Factura depende de Cliente
	private Cliente cliente;

	// Relaciona la factura con el producto (Relación ItemFactura)
	// Tipo relacion >> <Factura>:<Prodcuto> --> 1:1 Una factura puede contener
	// muchos, pocos o ningun producto
	@Autowired
	private List<ItemFactura> items;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, descripcion, items);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Factura))
			return false;
		Factura other = (Factura) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(items, other.items);
	}

	@Override
	public String toString() {
		return "Factura [descripcion=" + descripcion + ", cliente=" + cliente + ", items=" + items + "]";
	}

}
