package com.bolsadeideas.springboot.app.models.dao;

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
	//@Transactional(readOnly=true)//Envuelve el método como una transación
	@Override//
	public List<Cliente> findAll() {
		//Importae: Mapea a la entiddad no a la talba
		return em.createQuery("from Cliente").getResultList();
	}

	//@Transactional(readOnly = false)
	@Override
	public void save(Cliente cliente) {
		if(cliente.getId()!=null && cliente.getId()>0) {
			//Actualizar cliente
			em.merge(cliente);
		}else{
			//Registro de nuevo cliente
			em.persist(cliente);
		}
		
	}

	//@Transactional(readOnly=true)
	@Override
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}

	//@Transactional(readOnly=false)
	@Override
	public void delete(Long id) {
		//1º Buscamos el cliente a e liminar
		Cliente cliente = this.findOne(id);
		//2º Cliente esta en la base de datos?
		if(cliente!=null) {
			//Si, entonces elimnalo
			em.remove(cliente);
		}
		
		//opcion con menos codigo
		//em.remove(this.findOne(id));
	}
}
