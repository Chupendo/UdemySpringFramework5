package com.bolsadeideas.springboot.web.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources(value = { 
		@PropertySource("classpath:textos.properties"),
		@PropertySource("classpath:textos2.properties")
})
public class TextosProperitesConfig {

}
