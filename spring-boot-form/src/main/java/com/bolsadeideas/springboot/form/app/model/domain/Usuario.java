package com.bolsadeideas.springboot.form.app.model.domain;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

public class Usuario {

	@NotEmpty //Valida que nos sea vacio
	private String username;
	
	@NotEmpty //Valida que no sea vacio "requerido"
	private String password;
	private String email;
	
	
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
		return "Usuario [username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
	
}
