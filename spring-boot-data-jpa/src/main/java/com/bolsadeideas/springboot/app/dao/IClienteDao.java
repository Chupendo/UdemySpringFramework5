package com.bolsadeideas.springboot.app.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entities.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();
}
