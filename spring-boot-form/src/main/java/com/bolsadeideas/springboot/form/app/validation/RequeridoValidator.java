package com.bolsadeideas.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequeridoValidator implements ConstraintValidator<Requerido, String> {
	private Requerido requerido;

	// Se extrae la anotación y la capa inferior usa la reflexión para generar una
	// clase de p
	@Override
	public void initialize(Requerido constraint) {
		this.requerido = constraint;

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		return (value == null || value.isEmpty() || value.isBlank() || value.length() < this.requerido.length())
				? false
				: true;

	}

}
