package com.bolsadeideas.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.model.domain.Role;

@Service
public class RoleServiceImpl implements IRoleService {
		
	private List<Role> listaRoles; //Mock de datos
	
	public RoleServiceImpl() {
		//inicializamos los datos
		this.listaRoles = new ArrayList<>();
		listaRoles.add(new Role(1,"Administrador","ROLE_ADMIN"));
		listaRoles.add(new Role(2,"Usuario","ROLE_USER"));
		listaRoles.add(new Role(3,"Moderador","ROLE_MODERATOR")); 
	}

	@Override
	public List<Role> listar() {
		return listaRoles;
	}

	@Override
	public Role obtenerPorId(Integer id) {
		Role resultado = null;
		for(Role role:listaRoles) {
			if(role.getId()==id) {
				resultado = role;
				break; //salimos del for
			}
		}
		return resultado;
	}
}
