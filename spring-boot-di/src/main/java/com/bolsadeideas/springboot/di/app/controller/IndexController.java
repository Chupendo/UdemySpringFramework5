package com.bolsadeideas.springboot.di.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bolsadeideas.springboot.di.app.models.service.IServicio;
import com.bolsadeideas.springboot.di.app.models.service.MiServicio;

@Controller
public class IndexController {
	
	//Acople de una clase "service" al contralor
	//private MiServicio servicio = new MiServicio();
	
	//Inyección de dependencias(ID) DTO
	//@Autowired
	//private MiServicio servicio;
	
	//Inyección de dependencias por interfaz
	//@Autowired //por defecto coge la primea que encuentre anotada con componete e implemente la intefaz
	//@Qualifier("miServicioSimple") 
	//private IServicio servicio;
	
	//Inyección de dependencias de la interfaz de servicio por método setter
	/*
	private IServicio servicio;
	
	@Autowired
	public void setServicio(IServicio servicio) {
		this.servicio = servicio;
	}
	*/
	//Inyección de dependencias de la interfaz de servicio por constructor
	private IServicio servicio;
	
	@Autowired //Anotación opcional, vara ID del servicio primario vía constructor 
	public IndexController(IServicio servicio) {
		this.servicio=servicio;
	}
		
	@GetMapping(value= {"","/","index","home"})
	public String index(Model model) {
		
		//Pasmos a la vista el resutlado de la operaicon del dato que fuimos a buscar
		model.addAttribute("objeto",servicio.operacion());
		return "index";
	}
}
