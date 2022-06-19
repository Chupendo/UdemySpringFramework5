package com.example.springbootdatajpaimproved;

import com.example.springbootdatajpaimproved.service.IUploadsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDataJpaImprovedApplication implements CommandLineRunner {

    @Autowired
    IUploadsFileService uploadsFileService;
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataJpaImprovedApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        uploadsFileService.deleteAll();
        uploadsFileService.init();
    }
}
