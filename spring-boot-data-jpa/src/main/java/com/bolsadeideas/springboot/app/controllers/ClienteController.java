package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entities.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	
	@Autowired //Busca un componente o bean que implmente la IClienteDao
			   //La clase ClienteDaoImpl, implmenta la interfaz IClienteDao
			   //y esta registrado por medio de "@Repositoy"
	//private IClienteDao clienteDao;
	private IClienteService clienteSerivce;
	
	@RequestMapping(value="/listar",method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de clientes");
		//model.addAttribute("clientes",clienteDao.findAll());
		model.addAttribute("clientes",clienteSerivce.findAll());
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
	public String guardar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de cliente");
			return "form";
		}
		//System.out.println("cliente= "+cliente);
		//clienteDao.save(cliente);
		clienteSerivce.save(cliente);
		status.setComplete(); //Limpia los datos de la sesion
		return "redirect:/listar";
	}
	
	@RequestMapping(value="/form/{id}", method = RequestMethod.GET)
	public String editar(@PathVariable(value="id") Long id,Map<String,Object> model) {
		Cliente cliente = null;
		if(id>0) {
			//cliente = clienteDao.findOne(id);
			cliente = clienteSerivce.findOne(id);
		}else {
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de cliente");
		return "form";
	}
	
	
	//@DeleteMapping(value="/delete/{id}")
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String eliminar(@PathVariable(value="id") Long id,Map<String,Object> model) {
		if(id>0) {
			//clienteDao.delete(id);
			clienteSerivce.delete(id);
		}

		return "redirect:/listar";
	}
	
}
