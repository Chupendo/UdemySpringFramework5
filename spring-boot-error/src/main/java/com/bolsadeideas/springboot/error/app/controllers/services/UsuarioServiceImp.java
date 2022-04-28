package com.bolsadeideas.springboot.error.app.controllers.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.error.app.models.domain.Usuario;

@Service
public class UsuarioServiceImp implements IUsuarioService {

	private List<Usuario> lista;
	
	@PostConstruct
	public void init() {
		this.lista = new ArrayList<>();
		this.lista.add(new Usuario(1,"Andrés","Guzmán"));
		this.lista.add(new Usuario(2,"Pepa","García"));
		this.lista.add(new Usuario(3,"Lalo","Mena"));
		this.lista.add(new Usuario(4,"Luci","Fernández"));
		this.lista.add(new Usuario(5,"Paco","González"));
		this.lista.add(new Usuario(6,"Paco","Rodriguez"));
		this.lista.add(new Usuario(7,"Juan","Gomez"));
	}
	
	public UsuarioServiceImp() {}
	
	@Override
	public List<Usuario> listar() {		
		return this.lista;
	}

	@Override
	public Usuario obtenerPorId(Integer id) {
		Usuario resultado = null;
		/*
		 * for (Usuario u: this.lista){
		 * 	if(u.get().equals(id){
		 * 		resultado = u;
		 * 		break;	
		 * 	}
		 * }
		 * */
		resultado = lista.stream().filter(f->f.getId().equals(id)).findFirst().orElse(null);
		return resultado;
	}

	@Override
	public Optional<Usuario> obtenerPorIdOptional(Integer id) {
		Usuario usuario = this.obtenerPorId(id);
		return Optional.ofNullable(usuario);
	}

}
