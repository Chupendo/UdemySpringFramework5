package com.bolsadeideas.springboot.form.app.model.domain;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bolsadeideas.springboot.form.app.validation.IdentenficiadorRegex;
import com.bolsadeideas.springboot.form.app.validation.Requerido;



public class Usuario {

	//@Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d][-][A-Z]{1}") //Comentamos para que no se registre el error dos veces si se usa también en la clase validadora personalizada
	@IdentenficiadorRegex
	private String identificador;
	
	//Comentamos la anotacion para no validar dos veces al validar el campo en nuestra clase UsuarioValidador
	//@NotEmpty(message = "el nombre no puede ser vacio") //message es remplazado por el NotEmpty de messages.properties
	private String nombre;
	
	//@NotEmpty
	@Requerido
	private String apellido;
	
	//@NotEmpty //Valida que nos sea vacio
	@NotBlank //Valida que no sea vacio o no tenga espacios en blanco
	@Size(min=3, max=8)
	private String username;
	
	@NotEmpty //Valida que no sea vacio "requerido"
	private String password;
	
	@NotEmpty
	@Email(message = "correo con formato incorrecto")
	private String email;
	
	//Valida que no la propiedad cuenta no sea nulo y este entre 5 y 20
	@NotNull
	@Min(5)  //Mínimo 5
	@Max(20) //Maximo 20
	private Integer cuenta;
	
//	
//	//Valida que no la propiedad cuenta almenos tenga un dígito
//	@Min(1) //Mínimo 1 digito
//	private int cuenta;
//	
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
	
	public Integer getCuenta() {
		return cuenta;
	}
	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cuenta, email, identificador, password, username);
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
		return Objects.equals(cuenta, other.cuenta) && Objects.equals(email, other.email)
				&& Objects.equals(identificador, other.identificador) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "Usuario [identificador=" + identificador + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", username=" + username + ", password=" + password + ", email=" + email + ", cuenta=" + cuenta + "]";
	}
	
	
	
	
}
