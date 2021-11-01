package com.bolsadeideas.springboot.form.app.model.domain;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Usuario {

	@Pattern(regexp = "[0-9]{2}[.,][\\d]{3}[.,][\\d][-][a-zA-Z]{1}")
	private String identificador;
	
	@NotEmpty(message = "el nombre no puede ser vacio") //message es remplazado por el NotEmpty de messages.properties
	private String nombre;
	@NotEmpty
	private String apellido;
	
	@NotEmpty //Valida que nos sea vacio
	@Size(min=3, max=8)
	private String username;
	
	@NotEmpty //Valida que no sea vacio "requerido"
	private String password;
	
	@NotEmpty
	@Email(message = "correo con formato incorrecto")
	private String email;
	
	
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, password, username);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "Usuario [identificador=" + identificador + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
	
	
	
}
