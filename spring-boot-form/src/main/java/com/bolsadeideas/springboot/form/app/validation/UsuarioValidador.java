package com.bolsadeideas.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bolsadeideas.springboot.form.app.model.domain.Usuario;

@Component
public class UsuarioValidador implements Validator {

	@Override
	/*
	 * Determina el tipo de clase a validr
	 * 
	 * @param clazz:Class<?> Clase validar
	 * @return true si clazz es de tipo Usuario, false resto de casos
	 */
	public boolean supports(Class<?> clazz) {
		//Para validar que el objeto estamos validadno o que vamos a validar corresponde a la clase de tipo Usuario y no otro,
		//se ha de comprobar si la clase Usuario es asignable desde la clase que recibe el metodo como parámetro de entrada
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	/*
	 * Valida el objeto
	 * 
	 * @param targer:Objet Objeto con la informacion a validar
	 * @param error:Errors Objeto con la informacion de error, registrada por "Reject Error" de org.springframework.validation.ValidationUtils.
	 */
	public void validate(Object target, Errors errors) {
		//El param target en este caso es de tipo Usuario por lo que hacemos un cast
		Usuario usuario =(Usuario) target; //Casting

		//validar que el atributo "nombre" no sea vacío >> @NotEmapty
			//ValidationUtils.rejectIfEmpty(Errors, Fields, ErrorCode);
			//Errors: objeto que contiene el mensaje de error
			//Fields: mismo nombre del atributo en la clase pojo a validar
			//errorCode: Key del mensaje error registrado en el "messages.properties"
		//ValidationUtils.rejectIfEmpty(errors, "nombre", "NotEmpty.user.nombre"); //Validar vacío
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.user.nombre"); //Validar vacío y espacios en blanco
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "requerido.user.nombre"); //Validar vacío y espacios en blanco con mensaje personalizado
		
		//RejectIfEmpty es lo mismo que usar la estrucutra de control if con isEmpaty
		/*
		if(usuario.getNombre().isEmpty()) {
			errors.rejectValue("nombre", "NotEmpty.user.nombre");
		}
		*/
		
		//validar el identificador con expresiones reguales
		//El objeto String maneja por defecto expresiones reguales mediante el método matches
		if(usuario.getIdentificador().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")==false) {
			errors.rejectValue("identificador", "Pattern.user.identificador");	
		}
	}
}
