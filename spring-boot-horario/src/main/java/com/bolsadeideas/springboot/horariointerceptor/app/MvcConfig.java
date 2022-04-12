package com.bolsadeideas.springboot.horariointerceptor.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	//Inyecamos nuestro intercptor con el tipo de dato generíco
	@Autowired
	@Qualifier("horario")
	private HandlerInterceptor horario;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(horario); //No limitado, genera un loop al ejecutase tambien con las petiicones a la ruta de cerrado
		registry.addInterceptor(horario).excludePathPatterns("/control/cerrado");
	}

	
}
