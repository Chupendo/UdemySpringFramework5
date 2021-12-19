package com.bolsadeideas.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
	
	//Inyectamos nuestra clase validadora para el usuario
	@Autowired
	private UsuarioValidador validador;
	
	//Descomplamos nuestro validor del método hanlder "procesar" y se se encarge de gesitonarlo @Valid
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//Registramos e inyectamos nuestro validador
		//binder.setValidator(validador); //Remplaza el validador por defecto con anotaciones por el validador UsuarioValidador (solopermite clases personalidas)
		binder.addValidators(validador); //Agrega el validador UsuarioValidador (permite usar anotaciones + clases personaliadas)
		
		//Registramos e inyectamos nuestro editor de fechas (Date) personalizado [Filtro]: alternativa a @DateTimeFormat
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //Formato de fecha aceptado de "java.text.SimpleDateFormat"
		dateFormat.setLenient(false);//Permite que el intercpeto "dateFormat" sea tolerante al formato de la fecha recidibo "false" (lo autorrigue si puede, aunque puede generar error) o sea restritivo "true" (debe ser tal cual al definido)
		//@param Date.class Indicamos que el objeto que recibimos lo convertimos al tipo de dato "java.util.Date"
		//@param CustomDateEdito() Instancia de un editor de fechas personalizado de "org.springframework.beans.propertyeditors"
			//con el formato de fecha predefinio y si acepta (true) o no acepta (false) nulos
		//binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); //Se aplica a todos los objteso de la clase Date y no acepta vacíos
		binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(dateFormat, false));///Se aplica solo al campo "birthday" del formulario y acepta vacios
	}
	
	private static Logger _LOGG = LoggerFactory.getLogger(FormController.class);
	
	//Procesa la peticion de tipo get que va a form, para devolver el formualrio con los datos a procesar por otro handler.
	@GetMapping("/form")
	public String obtenerFormulario(Model model) {
		model.addAttribute("titulo", "Formulario usuarios");
		
		//Enviamso al formulario un usuario  con datos por defecto
		Usuario usuario = new Usuario();
		//Usuario con errores para comprobar la validacion
//		usuario.setIdentificador("123.456.789.K"); //Id erroneo, para chek la validacion
//		usuario.setNombre("Andres");
//		usuario.setApellido("Ruiz");
//		_LOGG.info("[obtenerFormulario] user created: "+usuario.toString());
		
		//Usuario con todos los datos menos con la fecha de cumpleaños
		usuario.setIdentificador("11.111.111-A");
		usuario.setApellido("Ruiz");
		usuario.setUsername("Chupendo");
		usuario.setPassword("123"); //El valor de la clave no aparecerá porque el cliente no lo añade
		usuario.setCuenta(5);
		usuario.setEmail("aruizpen@form.es");
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
