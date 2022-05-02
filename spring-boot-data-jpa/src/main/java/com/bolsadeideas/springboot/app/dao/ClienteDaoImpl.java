package com.bolsadeideas.springboot.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.entities.Cliente;

public class ClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)//Envuelve el método como una transación
	@Override
	public List<Cliente> findAll() {
		return em.createQuery("from Clientes").getResultList();
	}
}
