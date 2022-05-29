package com.example.springbootdatajpaimproved.service;

import com.example.springbootdatajpaimproved.dao.IClienteJPADao;
import com.example.springbootdatajpaimproved.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    IClienteJPADao clienteJPA;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteJPA.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteJPA.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOneBy(Long id) {
        return clienteJPA.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Cliente cliente) {
        clienteJPA.save(cliente);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteOneById(Long id) {
        if(id>0)
            clienteJPA.deleteById(id);
    }
}
