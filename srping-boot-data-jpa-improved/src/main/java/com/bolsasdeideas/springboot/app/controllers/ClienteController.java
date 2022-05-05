package com.bolsasdeideas.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bolsasdeideas.springboot.app.models.entities.Cliente;

@Controller
@SessionAttributes("client")
public class ClienteController {

	@GetMapping("/form")
	public String getForm(Model model) {
		Cliente cliente = null;
		model.addAttribute("client", cliente);
		model.addAttribute("title", "Spring Boot: Nuevo Cliente");
		return "form";
	}
}
