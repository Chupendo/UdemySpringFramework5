package com.bolsadeideas.springboot.form.app.model.domain;

import java.util.Objects;

public class Role {
	
	private Integer id;
	private String nombre;
	private String role;
	
	/* Constructor */
	public Role() {

	}
	
	public Role(Integer id, String nombre, String role) {
		this.id = id;
		this.nombre = nombre;
		this.role = role;
	}
	
	/* Getters & Setters */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, role);
	}

	//Método equal de Roel
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Role)) {
			return false;
		}
		Role other = (Role) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre) && Objects.equals(role, other.role);
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", nombre=" + nombre + ", role=" + role + "]";
	}
	
	

}
