package com.example.springbootdatajpaimproved;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfing implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        //Agregramos el recurso en nuestro proyecto
        registry.addResourceHandler("/uploads/**") //URL
                .addResourceLocations("file:/G:/apps/Spring/workspace/others/UdemySpringFramework5/spring-boot-data-jpa-improved/temp/uploads/"); //Ruta fisica
    }
}
