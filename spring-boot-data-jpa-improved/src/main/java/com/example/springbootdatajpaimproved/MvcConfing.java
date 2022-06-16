package com.example.springbootdatajpaimproved;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class MvcConfing implements WebMvcConfigurer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        //Agregramos el recurso en nuestro proyecto
        String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/uploads/**") //URL
                .addResourceLocations(resourcePath); //Ruta fisica
        log.info(resourcePath);
    }
}
