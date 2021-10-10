package com.bolsadeideas.springboot.web.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/params")
public class EjemploParamsController {
	private static Logger _log = LoggerFactory.getLogger(EjemploParamsController.class.toString());
	
	@GetMapping("/index")
	public String index(Model model) {
		return "params/index";
	}
	
	@GetMapping("/string")
	public String param(@RequestParam(name="texto",required = false) String texto, Model model) {
		_log.info("Start handler PARAM");
		
		model.addAttribute("resultado","El texto envadio es: "+texto);
		return "params/ver";
	}
}
