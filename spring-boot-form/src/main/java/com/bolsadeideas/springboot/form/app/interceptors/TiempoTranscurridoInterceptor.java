package com.bolsadeideas.springboot.form.app.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("TiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

	//Variable para mostrar informaicon en consola por medio del Log
	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getMethod().equalsIgnoreCase("post")) {
			//Si es post, omiitmos y nos salimos del interceptor
			return true;
		}
			
		if(handler instanceof HandlerMethod) {
			HandlerMethod metodo = (HandlerMethod) handler;
			logger.info("Es un método del controlador: " + metodo.getMethod().getName() );
		}
		logger.info("TiempoTranscurridoInterceptor : preHandle() entrando ... ");
		logger.info("Interceptando: "+handler);
		
		//Calcuamos el tiempo de inicio
		long timpoInicioMS = System.currentTimeMillis();//Hora en milisiegundos
		
		//Guardamos en el request
		request.setAttribute("timpoInicio", timpoInicioMS);

		//Generamos una demora aleatorio
		Random random = new Random();
		Integer demora = random.nextInt(500); //de 0 hasta 499 (500 milisegunds)
		Thread.sleep(demora); //Demora aleatoria
		
		/* Ejemplo de redireccion a otra ruta si no hay sesion*/
		/*
		System.out.println(request.getSession().getAttribute("user"));
		if(request.getSession().getAttribute("user")==null) {
			response.sendRedirect(request.getContextPath().concat("/loggin"));//En spring la ruta siempre parte de la ruta base (ruta de contexto) 	de nuestra app
			return false;
		}
		*/
		return true;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("post")) {
			//Si es post, omitimos y nos salimos del interceptor
			return;
		}
		//Calculamos el tiempo de fin
		long tiempoFinMS = System.currentTimeMillis();
		long timpoInicioMS = (Long) request.getAttribute("timpoInicio"); //Obtenemos el tiempo de inicio
					//Necesita un cast porque se "tiempoInicio" se guarda como Object, por lo que se deberá convertir
					//a tipo de objeto Long (recuerda que long es un primitivo, no es un objeto) o long, da lo mismo
		
		long tiempoTranscurridoMS = tiempoFinMS - timpoInicioMS;
		
		//Lo pasamos a la vista
		if(handler instanceof HandlerMethod && modelAndView!=null) {
			modelAndView.addObject("tiempoTranscurrido", tiempoTranscurridoMS);
			
		}
		
		logger.info("Tiempo Transcurrido: "+tiempoTranscurridoMS+" milisegudnos");
		logger.info("TiempoTranscurridoInterceptor : proHandle() saliend ... ");
		

	}

}
