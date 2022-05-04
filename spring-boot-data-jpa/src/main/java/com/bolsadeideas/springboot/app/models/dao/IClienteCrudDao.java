package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entities.Cliente;

//@Repository("repoCrudClient")
public interface IClienteCrudDao extends CrudRepository<Cliente, Long> {

}
