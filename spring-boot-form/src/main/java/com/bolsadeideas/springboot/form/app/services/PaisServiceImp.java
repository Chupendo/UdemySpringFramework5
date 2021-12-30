package com.bolsadeideas.springboot.form.app.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.model.domain.Pais;

@Service
public class PaisServiceImp implements IPaisService {

	private List<Pais> lista;
	
	public PaisServiceImp() {
		this.lista = Arrays.asList(new Pais(1,"ES","España"), 
				new Pais(1,"MX","Mexico"),
				new Pais(2,"CH","Chile"),
				new Pais(3,"AR","Argentina"),
				new Pais(4,"PE","Perú"),
				new Pais(5,"CO","Colombia"),
				new Pais(6,"VE","Venezuela"));
	}
	@Override
	public List<Pais> listar() {
		
		return lista;
	}

	@Override
	public Pais obtenerPais(Integer id) {
		//
		Pais resultado = null;
		for(Pais pais: lista) {
			if(id==pais.getId()) {
				resultado = pais;
				break;
			}
		}
		return resultado;
	}

}
