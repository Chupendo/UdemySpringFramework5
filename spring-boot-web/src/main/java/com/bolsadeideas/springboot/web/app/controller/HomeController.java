package com.bolsadeideas.springboot.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value="/")
	public String home() {
		//Redirigir -> Reiniiza la petición
		//Cambiar la URL del navegador
		//return "redirect:https://www.google.es";
		//return "redirect:/app/home";
		
		//Carga (no reiniciar la petición "requets" -> pasar parametos de un método Handler a otro
		//No cambia la url del navegador.
		//return "forward:https://www.google.es"; //No soportado
		return "forward:/app/home";
	}
}
