package com.bolsadeideas.springboot.form.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bolsadeideas.springboot.form.app.model.domain.Usuario;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("user")
public class FormController {
	
	@Autowired
	private UsuarioValidador validador;
	
	//Descomplamos nuestro validor del método hanlder "procesar" y se se encarge de gesitonarlo @Valid
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//Registramos e inyectamos nuestro validador
		binder.setValidator(validador); //Remplaza el validador por defecto con anotaciones por el validador UsuarioValidador
		//binder.addValidators(validador); //Agrega el validador UsuarioValidador
	}
	
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
		
		//Validamoss el objeto mediante nuestra clase validadora y registrmoas los errores
		//validador.validate(usuario, result); //Lo automatizamos con @InitBinder
		
		//Trabajo automática e implicita de los errores con Thymelaf y Spring, en la vista
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario usuarios");
			return "form";
		}
		//Si no hay errores, se procesan los datos
		model.addAttribute("titulo", "Resultado form");
		model.addAttribute("usuario", usuario);
		
		return "resultado";
	}
}
