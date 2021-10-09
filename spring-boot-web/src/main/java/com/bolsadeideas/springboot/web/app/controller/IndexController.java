package com.bolsadeideas.springboot.web.app.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/app")
public class IndexController {

	//Métodos de acción o Handlers
	
	@RequestMapping(value = {"/","/index","home"},method = RequestMethod.GET) //@GetMapping({"/","/index","home"})
	public ModelAndView index(ModelAndView mv) {
		
		//Agregramos datos a la vista como un mapa de valores {key,value}
		//model.addAttribute("titulo","Hola Spring Framework!");
		//model.put("titulo","Hola Spring Framework!");
		mv.addObject("titulo","Hola Spring Framework! con ModelAndView");
		
		//nombre de la vista
		mv.setViewName("index");
		//return "index";
		return mv;
	}
}
