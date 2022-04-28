package com.bolsadeideas.springboot.error.app.errors;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String id) {
		super("Usuario con ID: ".concat(id).concat(" no existe en el sistema"));
	}
	
}
