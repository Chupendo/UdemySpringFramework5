package com.bolsadeideas.springboot.di.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.bolsadeideas.springboot.di.app.models.service.IServicio;
import com.bolsadeideas.springboot.di.app.models.service.MiServicio;
import com.bolsadeideas.springboot.di.app.models.service.MiServicioComplejo;

@Configuration
public class AppConfig {
	//Candidos o lista de dependencias
	
	//La dependcnia IServicio tiene dos candidatos posibles, por lo que requiere el uso de Qualifier o Primary
	@Bean("miServicioSimple")
	@Primary
	public IServicio registrarMiSerivcio() {
		//Creamo la instanica
		return new MiServicio();
	}
	
	@Bean("miServicioComplejo")
	public IServicio registrarMiSerivcioComplejo() {
		//Creamo la instanica
		return new MiServicioComplejo();
	}
}
