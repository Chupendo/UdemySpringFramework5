package com.example.springbootdatajpaimproved.dao;


import com.example.springbootdatajpaimproved.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteJPADao extends JpaRepository<Cliente,Long> {
}
