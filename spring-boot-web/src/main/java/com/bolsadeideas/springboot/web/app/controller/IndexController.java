package com.bolsadeideas.springboot.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping; //import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	//Métodos de acción o Handlers
	
	@RequestMapping(value = {"/","/index","home"},method = RequestMethod.GET) //@GetMapping({"/","/index","home"})
	public String index(Model model) {
		
		//Agregramos datos a la vista como un mapa de valores {key,value}
		model.addAttribute("titulo","Hola Spring Framework!");
		//nombre de la vista
		return "index";
	}
}
