package com.bolsadeideas.springboot.form.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bolsadeideas.springboot.form.app.model.domain.Usuario;

@Controller
public class FormController {

	//Procesa la peticion de tipo get que va a form, para devolver el formualrio con los datos a procesar por otro handler.
	@GetMapping("/form")
	public String obtenerFormulario(Model model) {
		model.addAttribute("titulo", "Formulario usuarios");
		
		//enviar un objeto vacio tipo usuario, se puede usar el operador '?' como alternativa en el value de los input para que no de error
		model.addAttribute("user",new Usuario());
		
		return "form";
	}
	

	//Procesa la peticion de tipo post que va a form, para obtener los datos.
	@PostMapping("/form")
	public String leerFormulario(@Valid @ModelAttribute("user") Usuario usuario,
			BindingResult result,
			Model model) {
		
		if(result.hasErrors()) {
			//Si existe errores de validacion, entonces enviamos por ejemplo los mensajes de error
			//Mapa {atributo con error,mensaje} que recoge los menjaes de errores FieldErros
			Map<String,String> errores = new HashMap<>();
			
			//Recogemos los errores para cada atributo de una lista 
			result.getFieldErrors().forEach(err->{
				//Mapeamoss la listade FieldErrores
				errores.put(err.getField(), 
						"El campo "
						.concat(err.getField())
						.concat(" ")
						.concat(err.getCode())
						);
			});
			
			//Enviamos los mensajesde la vista del formulario
			model.addAttribute("error",errores);
			return "form";
		}
		
		//Si no hay errores, se procesan los datos
		model.addAttribute("titulo", "Resultado form");
		model.addAttribute("usuario", usuario);
		
		return "resultado";
	}
}
