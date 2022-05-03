package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.app.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entities.Cliente;

@Controller
public class ClienteController {

	@Autowired //Busca un componente o bean que implmente la IClienteDao
			   //La clase ClienteDaoImpl, implmenta la interfaz IClienteDao
			   //y esta registrado por medio de "@Repositoy"
	private IClienteDao clienteDao;
	
	@RequestMapping(value="/listar",method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de clientes");
		model.addAttribute("clientes",clienteDao.findAll());
		return "listar";
	}
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		//PasAr datos a la vista (alternativa a Model)
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de cliente");
		return "form";
	}

	@RequestMapping(value="/form", method = RequestMethod.POST)
	public String guardar(Cliente cliente) {
		System.out.println("cliente= "+cliente);
		clienteDao.save(cliente);
		return "redirect:/listar";
	}
}
