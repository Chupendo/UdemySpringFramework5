package com.example.springbootdatajpaimproved.service;

import com.example.springbootdatajpaimproved.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAll();
    public Page<Cliente> findAll(Pageable pageable);
    public Cliente findOneBy(Long id);
    public void save(Cliente cliente);
    public void deleteOneById(Long id);
}
