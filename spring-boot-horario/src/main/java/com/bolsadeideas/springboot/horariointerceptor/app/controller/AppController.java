package com.bolsadeideas.springboot.horariointerceptor.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/control")
public class AppController {

	@Value("${config.horario.apertura}")
	private Integer apertura;
	@Value("${config.horario.cierre}")
	private Integer cierre;
	
	
	// Metodo handler
	@GetMapping({"/","index"})
	public String index(Model model) {
		model.addAttribute("titulo", "Bienvenido al horario de atención a clientes");

		return "index";
	}
	
	@GetMapping({"/cerrado"})
	public String cerrado(Model model) {
		//Generamos mensaje para la vista
		StringBuilder mensaje = new StringBuilder("Cerrado, por favos visítenos desde las ");
		mensaje.append(apertura);
		mensaje.append(" hrs., hasta las ");
		mensaje.append(cierre);
		mensaje.append(". Gracias por su visita");
		
		model.addAttribute("titulo","Fuera del horario de atención");
		model.addAttribute("mensaje",mensaje.toString());
		return "cerrado";
	}
}
