package com.bolsadeideas.springboot.form.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bolsadeideas.springboot.form.app.model.domain.Usuario;

@Controller
@SessionAttributes("user")
public class FormController {

	private static Logger _LOGG = LoggerFactory.getLogger(FormController.class);
	
	//Procesa la peticion de tipo get que va a form, para devolver el formualrio con los datos a procesar por otro handler.
	@GetMapping("/form")
	public String obtenerFormulario(Model model) {
		model.addAttribute("titulo", "Formulario usuarios");
		
		//Enviamso al formulario un usuario  con datos por defecto
		Usuario usuario = new Usuario();
		usuario.setIdentificador("123.456.789.K");
		usuario.setNombre("Andres");
		usuario.setApellido("Ruiz");
		_LOGG.info("[obtenerFormulario] user created: "+usuario.toString());
		model.addAttribute("user",usuario);
		
		return "form";
	}
	

	//Procesa la peticion de tipo post que va a form, para obtener los datos.
	@PostMapping("/form")
	public String leerFormulario(@Valid @ModelAttribute("user") Usuario usuario,
			BindingResult result,
			Model model) {
		
		_LOGG.info("[leerFormulario] user recived: "+usuario.toString());
		//Trabajo manual y explicita de los errores
		/*
		if(result.hasErrors()) {
			//Si existe errores de validacion, entonces enviamos por ejemplo los mensajes de error
			//Mapa {atributo con error,mensaje} que recoge los menjaes de errores FieldErros
			Map<String,String> errores = new HashMap<>();
			
			//Recogemos los errores para cada atributo de una lista 
			result.getFieldErrors().forEach(err->{
				//Mapeamoss la listade FieldErrores
				errores.put(err.getField(), 
						"El campo "
						.concat(erretField())
						.concat(" ")
						.concat(err.getCode())
						);
			});
		 
			//Enviamos los mensajesde la vista del formulario
			model.addAttribute("error",errores);
			return "form";
		}
		*/
		
		//Trabajo automática e implicita de los errores con Thymelaf y Spring, en la vista
		if(result.hasErrors()) {
			return "form";
		}
		//Si no hay errores, se procesan los datos
		model.addAttribute("titulo", "Resultado form");
		model.addAttribute("usuario", usuario);
		
		return "resultado";
	}
}
