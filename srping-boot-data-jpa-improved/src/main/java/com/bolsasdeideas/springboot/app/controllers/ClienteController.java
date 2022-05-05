package com.bolsasdeideas.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bolsasdeideas.springboot.app.models.entities.Cliente;

@Controller
@SessionAttributes("client")
public class ClienteController {

	@RequestMapping(value = "/form",method = RequestMethod.GET)
	public String getForm(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("client",cliente);
		model.addAttribute("title","Spring Boot: Registro de Cliente");
		return "form";
	}
	
	@RequestMapping(value="/form")
	public String processForm(@Valid @ModelAttribute("client") Cliente client, 
			BindingResult result, Map<String, Object> model){
		if(result.hasErrors()) {
			model.put("title","Spring Boot: Registro de Cliente");
			return "form";
		}
		return "list";
	}
	
}
