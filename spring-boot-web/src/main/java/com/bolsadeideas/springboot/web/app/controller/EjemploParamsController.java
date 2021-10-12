package com.bolsadeideas.springboot.web.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/params")
public class EjemploParamsController {
	private static Logger _log = LoggerFactory.getLogger(EjemploParamsController.class.toString());

	@GetMapping("/index")
	public String index(Model model) {
		return "params/index";
	}

	@GetMapping("/string")
	public String param(@RequestParam(name = "texto", required = false) String texto, Model model) {
		_log.info("Start handler PARAM simple");

		model.addAttribute("resultado", "El texto envadio es: " + texto);
		return "params/ver";
	}

	@GetMapping("/mix-params")
	public String param(@RequestParam String saludo, @RequestParam Integer numero, Model model) {
		_log.info("Start handler PARAM with multiple params and using the anottation RequestParam");

		model.addAttribute("resultado", "El saludo es '" + saludo + "', y el número es '" + numero + "'");
		return "params/ver";
	}
	
	@GetMapping("/mix-params-request")
	public String param(HttpServletRequest request, Model model) {
		_log.info("Start handler MIX-PARAM with multiple params and using the object HttpServletRequest");
		
		String saludo = request.getParameter("saludo");
		Integer numero = null;
		try {
			numero = Integer.parseInt(request.getParameter("numero"));
		}catch(NumberFormatException e){
			numero = null;
		}
		model.addAttribute("resultado", "El saludo es '" + saludo + "', y el número es '" + numero + "'");
		return "params/ver";
	}
}
