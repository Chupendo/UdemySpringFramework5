package com.bolsadeideas.springboot.web.app.controller;

//import java.util.Map;

import org.springframework.stereotype.Controller;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap; 
//import java.util.Map;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; //import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	// Métodos de acción o Handlers

	@RequestMapping(value = { "/", "/index", "home" }, method = RequestMethod.GET) // @GetMapping({"/","/index","home"})
	public ModelAndView index(ModelAndView mv) {

		// Agregramos datos a la vista como un mapa de valores {key,value}
		// model.addAttribute("titulo","Hola Spring Framework!");
		// model.put("titulo","Hola Spring Framework!");
		mv.addObject("titulo", "Hola Spring Framework! con ModelAndView");

		// nombre de la vista
		mv.setViewName("index");
		// return "index";
		return mv;
	}

	// Devolver un Objeto String y modificar cabeceras
	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		HttpHeaders headers = new HttpHeaders();

		return new ResponseEntity<>("This is a String and Custom header set", headers, HttpStatus.OK);
	}

	// http://localhost:8080/JSONObject/1
	@RequestMapping(value = "JSONObject/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> ApiCall(@PathVariable(name = "id") long id) throws JSONException {
		JSONObject resp = new JSONObject();
		resp.put("status", 0);
		resp.put("id", id);

		return new ResponseEntity<String>(resp.toString(), HttpStatus.CREATED);
	}
	// Devolver un Json

	// http://localhost:8080/gson
	private static final Gson gson = new Gson();

	@RequestMapping(value = "gson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> so() {
		Gson gson = new Gson();
		int[] ints = { 1, 2, 3, 4, 5 };
		String[] strings = { "abc", "def", "ghi" };

		// (Serialization)
		gson.toJson(ints); // ==> prints [1,2,3,4,5]
		gson.toJson(strings); // ==> prints ["abc", "def", "ghi"]

		// (Deserialization)
		int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);

		return ResponseEntity.ok(gson.toJson(ints));
	}

	// http://localhost:8080/responseBody
	@RequestMapping(value = "responseBody", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String so2() {
		return "\"{\"vale\" : \"This is a String\"}\"";
	}

}
