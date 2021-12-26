package com.bolsadeideas.springboot.form.app.model.domain;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Pais {
	
	//@NotNull
	private Integer id;
	
	@NotEmpty
	private String codigo;
	private String nombre;
	
	//Constructores
	public Pais() {

	}
	public Pais(Integer id, String codigo, String nombre) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	//Getters & Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//hashCode & equals
	@Override
	public int hashCode() {
		return Objects.hash(codigo, id, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Pais)) {
			return false;
		}
		Pais other = (Pais) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre);
	}
	
	//toString
	@Override
	public String toString() {
		return "Pais [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	
}
