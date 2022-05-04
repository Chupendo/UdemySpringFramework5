package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IClienteCrudDao;
import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entities.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	//private IClienteDao clienteDAO;
	private IClienteCrudDao clienteDAO; //CrudRepository
	
	@Transactional(readOnly=true)//Envuelve el método como una transación
	@Override
	public List<Cliente> findAll() {
		//return clienteDAO.findAll();
		return (List<Cliente>) clienteDAO.findAll();
	}

	@Transactional(readOnly = false)
	@Override
	public void save(Cliente cliente) {
		clienteDAO.save(cliente);
		
	}

	@Transactional(readOnly=true)
	@Override
	public Cliente findOne(Long id) {
		//return clienteDAO.findOne(id);
		return clienteDAO.findById(id).orElse(null);
	}

	@Transactional(readOnly=false)
	@Override
	public void delete(Long id) {
		//clienteDAO.delete(id);
		clienteDAO.deleteById(id);
	}

}
