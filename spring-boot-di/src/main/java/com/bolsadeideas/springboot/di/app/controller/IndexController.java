package com.bolsadeideas.springboot.di.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bolsadeideas.springboot.di.app.models.service.MiServicio;

@Controller
public class IndexController {
	
	//Acople de una clase "service" al contralo
	private MiServicio servicio = new MiServicio();
	
	@GetMapping(value= {"","/","index","home"})
	public String index(Model model) {
		
		//Pasmos a la vista el resutlado de la operaicon del dato que fuimos a buscar
		model.addAttribute("objeto",servicio.operacion());
		return "index";
	}
}
