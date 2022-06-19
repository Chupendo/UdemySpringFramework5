package com.example.springbootdatajpaimproved.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface IUploadsFileService {
    public Resource load (String filename) throws MalformedURLException;
    public String copy (MultipartFile file) throws IOException;
    public boolean delete(String filename);
    public Path getPaths(String filename);
    public void deleteAll();
    public void init() throws IOException;
}
