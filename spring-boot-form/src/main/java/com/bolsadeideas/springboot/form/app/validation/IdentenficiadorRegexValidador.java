package com.bolsadeideas.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentenficiadorRegexValidador implements ConstraintValidator<IdentenficiadorRegex, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		///Return true	si "identificar" cumple con la expresión regular
		// Return false	resto de caos
		if(value.matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")) {
			return true;
		}
		return false;
	}

}
