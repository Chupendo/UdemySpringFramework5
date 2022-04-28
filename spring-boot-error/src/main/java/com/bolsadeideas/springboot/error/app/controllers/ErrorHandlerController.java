package com.bolsadeideas.springboot.error.app.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bolsadeideas.springboot.error.app.errors.UserNotFoundException;

@ControllerAdvice
public class ErrorHandlerController {

	@ExceptionHandler(ArithmeticException.class )
	public String aritemticaError(Exception ex,Model model) {
		model.addAttribute("error","Error de aritmética");
		model.addAttribute("message",ex.getMessage());
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp",new Date());
		return "error/generica";
		//return "error/aritmetica";
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public String numberFormatError(Exception ex,Model model) {
		model.addAttribute("error","Error al combertir el dato a entero");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp",new Date());
		return "error/generica";
		//return "error/numberFormat";
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public String usuarioNoEncontrado(Exception ex,Model model) {
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp",new Date());		
		return "error/usuarioNoEncontrado";
	}
}
