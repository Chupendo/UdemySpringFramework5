package com.bolsadeideas.springboot.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.entities.Cliente;

@Repository("clienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)//Envuelve el método como una transación
	@Override//
	public List<Cliente> findAll() {
		//Importae: Mapea a la entiddad no a la talba
		return em.createQuery("from Cliente").getResultList();
	}

	@Transactional(readOnly = false)
	@Override
	public void save(Cliente cliente) {
		em.persist(cliente);
	}
}
