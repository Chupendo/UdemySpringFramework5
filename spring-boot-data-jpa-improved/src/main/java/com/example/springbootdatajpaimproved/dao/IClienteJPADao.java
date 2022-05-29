package com.example.springbootdatajpaimproved.dao;


import com.example.springbootdatajpaimproved.entity.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IClienteJPADao extends PagingAndSortingRepository<Cliente,Long> {
}
