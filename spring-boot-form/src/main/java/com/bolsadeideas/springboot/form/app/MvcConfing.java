package com.bolsadeideas.springboot.form.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfing implements WebMvcConfigurer {

	//Inyectamos nuestro interpector por interfaz y selecionamos el candidato.
	@Autowired
	@Qualifier("TiempoTranscurridoInterceptor")
	private HandlerInterceptor tiempoTranscurridoInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//Implementación en todos las rutas o patrones de la aplicación
		registry.addInterceptor(tiempoTranscurridoInterceptor);
	}
	
}
