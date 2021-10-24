package com.bolsadeideas.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bolsadeideas.springboot.form.app.model.domain.Usuario;

@Controller
public class FormController {

	//Procesa la peticion de tipo post que va a form, para devolver el formualrio con los datos a procesar por otro handler.
	@GetMapping("/form")
	public String obtenerFormulario(Model model) {
		model.addAttribute("titulo", "Formulario usuarios");
		return "form";
	}
	

	//Procesa la peticion de tipo post que va a form, para obtener los datos.
	@PostMapping("/form")
	public String leerFormuario(Model model,
			@RequestParam(name = "username") String username,
			@RequestParam(value = "password") String pwd,
			@RequestParam String email) {
		
		Usuario usuario = new Usuario();
		
		usuario.setUsername(username);
		usuario.setPassword(pwd);
		usuario.setEmail(email);
		
		model.addAttribute("titulo", "Resutlado form");
		model.addAttribute("usuario", usuario);
		
		return "resultado";
	}
}
