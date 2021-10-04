package com.bolsadeideas.springboot.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; //import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	//Métodos de acción o Handlers
	
	@RequestMapping(value = {"/","/index","home"},method = RequestMethod.GET) //@GetMapping({"/","/index","home"})
	public String index() {
		
		//nombre de la vista
		return "index";
	}
}
