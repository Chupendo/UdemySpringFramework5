package com.bolsadeideas.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.app.dao.IClienteDao;

@Controller
public class ClienteController {

	@Autowired //Busca un componente o bean que implmente la IClienteDao
			   //La clase ClienteDaoImpl, implmenta la interfaz IClienteDao
			   //y esta registrado por medio de "@Repositoy"
	private IClienteDao clienteDao;
	
	@RequestMapping(value="listar",method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de clientes");
		model.addAttribute("clinetes",clienteDao.findAll());
		return "listar";
	}
}
