package com.bolsadeideas.springboot.web.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.web.app.model.Usuario;


@Controller
@RequestMapping(value="/app")
public class IndexController {
	private static Logger _log = LoggerFactory.getLogger(IndexController.class.toString());
	
	@Value("${texto.indexcontroller.index.titulo}")
	private String textoIndex;
	@Value("${texto.indexcontroller.index.perfil}")
	private String textoPerfil;
	@Value("${texto.indexcontroller.index.listar}")
	private String textoListar;
	
	//Variavle del sistema y valor por defecto
	@Value("${java.home}")
	private String java;
	
	@Value("${username:Andres}")
	private String userName;
	

	
	@RequestMapping(value = {"/","/index","home"},method = RequestMethod.GET) //@GetMapping({"/","/index","home"})
	public String index(Model model) {
		_log.info("Start handler INDEX");
		
		//Agregramos datos a la vista como un mapa de valores {key,value}
		model.addAttribute("titulo",textoIndex);
		//nombre de la vista
		return "index";
	}
	
	@RequestMapping(value="/perfil")
	public String perfil(Model model) {
		_log.info("Start handler PERFIL");
		
		//Cremoas nuestro objeto que representa los datos
		Usuario usuario = new Usuario();
		usuario.setNombre("Andres");
		usuario.setApellido("Ruiz");
		
		model.addAttribute("usuario",usuario);
		_log.debug("usuario: "+usuario);
		
		//Se lo pasamso a la vista para que esta los redenderice
		model.addAttribute("titulo", textoPerfil+usuario.getNombre());
		
		//Devolvemos la vista
		return "perfil";
	}
	
	@RequestMapping(value="/listar")
	public String listar(Model model) {
		_log.info("Start handler LISTAR");
		/*
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("Andres","Ruiz","andres@correo.com"));
		usuarios.add(new Usuario("John","Doe","john@correo.com"));
		usuarios.add(new Usuario("Jane","Doe","jane@correo.com"));
		*/
		model.addAttribute("titulo", textoListar);
		//model.addAttribute("usuarios", usuarios);
		return "listar";
	}
	
	//Parametros que estan presentes en todos los metodos Handerl del Controlador
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(){
		_log.info("Start handler POBLARUSUARIOS");
		List<Usuario> usuarios = new ArrayList<>();
		
		//usuarios.add(this.users.get(0));
		usuarios.add(new Usuario("Andres","Ruiz","andres@correo.com"));
		usuarios.add(new Usuario("John","Doe","john@correo.com"));
		usuarios.add(new Usuario("Jane","Ruth","jane@correo.com"));
		return usuarios;
	}
}
