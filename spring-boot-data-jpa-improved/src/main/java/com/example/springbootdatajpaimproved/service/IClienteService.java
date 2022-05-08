package com.example.springbootdatajpaimproved.service;

import com.example.springbootdatajpaimproved.entity.Cliente;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAll();
    public Cliente findOneBy(Long id);
    public void save(Cliente cliente);
    public void deleteOneById(Long id);
}
