package com.bolsadeideas.springboot.web.app.model;

import java.util.Objects;

public class Usuario {
	private static Long cont = 0L;
	private Long idUsuario;
	private String nombre;
	private String apellido;
	
	Usuario(){
		cont++;
		idUsuario = cont;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, idUsuario, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(idUsuario, other.idUsuario)
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	
}
