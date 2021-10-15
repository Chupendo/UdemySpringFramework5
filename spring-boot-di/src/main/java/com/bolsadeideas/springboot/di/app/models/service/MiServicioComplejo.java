package com.bolsadeideas.springboot.di.app.models.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("miServicioComplejo")
//@Service
public class MiServicioComplejo implements IServicio{
	//Si hubiera un constructor con parámetros se debe crear uno por defecto (Sin parámetros) de lo contrario daría error al crear la instancia por parte del contenedor de dependencias de spring.
	
	@Override //Esta anotación indica que es una operación del padre
	public String operacion() {
		return "ejectuando algún proceso imporante complejo...";
	}
}
