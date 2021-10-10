package com.bolsadeideas.springboot.web.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.web.app.model.Usuario;


@Controller
@RequestMapping(value="/app")
public class IndexController {
	private static Logger _log = LoggerFactory.getLogger(IndexController.class.toString());
	
	//Métodos de acción o Handlers
	
	@RequestMapping(value = {"/","/index","home"},method = RequestMethod.GET) //@GetMapping({"/","/index","home"})
	public String index(Model model) {
		_log.info("Start handler INDEX");
		
		//Agregramos datos a la vista como un mapa de valores {key,value}
		model.addAttribute("titulo","Hola Spring Framework! con Model");
		//nombre de la vista
		return "index";
	}
	
	@RequestMapping(value="/perfil")
	public String perfil(Model model) {
		_log.info("Start handler PERFIL");
		
		//Cremoas nuestro objeto que representa los datos
		Usuario usuario = new Usuario();
		usuario.setNombre("Andres");
		usuario.setApellido("Ruiz");
		model.addAttribute("usuario",usuario);
		_log.debug("usuario: "+usuario);
		
		//Se lo pasamso a la vista para que esta los redenderice
		model.addAttribute("titulo", "Perfil del usuario: "+usuario.getNombre());
		
		//Devolvemos la vista
		return "perfil";
	}
}
