package com.bolsadeideas.springboot.error.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bolsadeideas.springboot.error.app.controllers.services.IUsuarioService;
import com.bolsadeideas.springboot.error.app.errors.UserNotFoundException;
import com.bolsadeideas.springboot.error.app.models.domain.Usuario;

@Controller
public class AppController {

	@Autowired
	private IUsuarioService daoUsuario;
	
	@GetMapping("/index")
	public String index() {
		//Integer valor = 100/0;
		Integer valor = Integer.parseInt("A");
		return "index";
	}
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Integer id, Model model) {
		Usuario usuario  = daoUsuario.obtnerPorId(id);
		if(usuario==null) {
			throw new UserNotFoundException(id.toString());
		}
		model.addAttribute("usuario",usuario);
		model.addAttribute("titulo","Detalle usuario: ".concat(usuario.getNombre()));
		return "ver";
	}
}
