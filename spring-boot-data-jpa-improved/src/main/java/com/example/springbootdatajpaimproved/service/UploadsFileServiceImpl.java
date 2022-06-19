package com.example.springbootdatajpaimproved.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadsFileServiceImpl implements IUploadsFileService {
    private static final String URI = "uploads";
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public Resource load (String filename) throws MalformedURLException {
        Path rootAbsolutePath = this.getPaths(filename);
        log.info("rootAbsolutePath: " + rootAbsolutePath.toString());
        Resource recurso = null;

        recurso = new UrlResource(rootAbsolutePath.toUri());
        if (!recurso.exists() && !recurso.isReadable()) {
            throw new RuntimeException("Error: no se puede cargar " + rootAbsolutePath.getFileName());
        }
        return recurso;
    }

    public String copy (MultipartFile file) throws IOException {
            String uniqueFilename = UUID.randomUUID().toString();
            String fileName = uniqueFilename+"_"+file.getOriginalFilename();

            Path rootAbsolutePath = getPaths(fileName);
            log.info("rootAbsolutePath: "+rootAbsolutePath);

            //Comprobamos si exsite el direcotior de recursos del cliente
            File directorioRecursosCliente = new File(rootAbsolutePath.getFileName().toString());
            if (!directorioRecursosCliente.exists()) {
               //Si no existe lo creamos
               if (directorioRecursosCliente.mkdirs()) {
                    log.info("directory create: "+directorioRecursosCliente);
               } else {
                    throw new IOException("Error al crear el directorio "+directorioRecursosCliente);
               }
            }
            Files.copy(file.getInputStream(),rootAbsolutePath);
            return fileName;
    }

    public boolean delete(String filename){
        Path rootAbsolutePath = getPaths(filename);
        File archivo = rootAbsolutePath.toFile();
        if(archivo.exists() && archivo.canRead()){
            if(archivo.delete()) {
                return true;
            }
        }
        return false;
    }
    public Path getPaths(String filename){
        return Paths.get(URI).resolve(filename).toAbsolutePath();
    }
}
