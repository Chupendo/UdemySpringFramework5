package com.bolsadeideas.springboot.di.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.bolsadeideas.springboot.di.app.models.domain.ItemFactura;
import com.bolsadeideas.springboot.di.app.models.domain.Producto;
import com.bolsadeideas.springboot.di.app.models.service.IServicio;
import com.bolsadeideas.springboot.di.app.models.service.MiServicio;
import com.bolsadeideas.springboot.di.app.models.service.MiServicioComplejo;

@Configuration
public class AppConfig {
	// Candidatos o lista de dependencias

	// La dependencia IServicio tiene dos candidatos posibles, por lo que requiere
	// el uso de Qualifier o Primary
	@Bean("miServicioSimple")
	@Primary
	public IServicio registrarMiSerivcio() {
		// Creamo la instanica
		return new MiServicio();
	}

	@Bean("miServicioComplejo")
	public IServicio registrarMiSerivcioComplejo() {
		// Creamo la instanica
		return new MiServicioComplejo();
	}

	// Dependencia para los ItemFactura
	@Bean("itemsFactura")
	public List<ItemFactura> registrarItems() {
		// Creamos unos productos
		Producto producto1 = new Producto("Camara Sony", 100);
		Producto producto2 = new Producto("Bicicleta Bianchi aro 26", 200);

		// Añadimos los productos a un ItemFactura, un item por producto
		ItemFactura linea1 = new ItemFactura(producto1, 2);
		ItemFactura linea2 = new ItemFactura(producto2, 4);

		// Creamos la instancia con informaicón y la devolvemos
		return Arrays.asList(linea1, linea2);
	}

	// Dependencia para los ItemFactura de Oficina
	@Bean("itemsFacturaOficina")
	@Primary
	public List<ItemFactura> registrarItemsOficina() {
		// Creamos unos productos
		Producto producto1 = new Producto("Monitor LG LCD 24 \" ", 250);
		Producto producto2 = new Producto("Notebook Asus", 500);
		Producto producto3 = new Producto("Impresora HP Multifuncional", 80);
		Producto producto4 = new Producto("Escritorio Ofciona", 300);
		// Añadimos los productos a un ItemFactura, un item por producto
		ItemFactura linea1 = new ItemFactura(producto1, 2);
		ItemFactura linea2 = new ItemFactura(producto2, 1);
		ItemFactura linea3 = new ItemFactura(producto3, 1);
		ItemFactura linea4 = new ItemFactura(producto4, 1);

		// Creamos la instancia con informaicón y la devolvemos
		return Arrays.asList(linea1, linea2, linea3, linea4);
	}
}
