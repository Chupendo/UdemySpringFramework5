package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entities.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDAO;
	
	@Override
	public List<Cliente> findAll() {
		return clienteDAO.findAll();
	}

	@Override
	public void save(Cliente cliente) {
		clienteDAO.save(cliente);
		
	}

	@Override
	public Cliente findOne(Long id) {
		return clienteDAO.findOne(id);
	}

	@Override
	public void delete(Long id) {
		clienteDAO.delete(id);
	}

}
