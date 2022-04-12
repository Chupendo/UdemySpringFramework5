package com.bolsadeideas.springboot.horariointerceptor.app.interceptors;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("horario")
public class HorarioInterceptor implements HandlerInterceptor {

	@Value("${config.horario.apertura}")
	private Integer apertura;
	@Value("${config.horario.cierre}")
	private Integer cierre;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//Obtenemos la hora acutal del día
		Calendar calendar = Calendar.getInstance();
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		
		//Comprobamos si esta dentro del rango
		if(hora>=apertura && hora<cierre) {//Esta en dentro del horario
			StringBuilder mensaje = new StringBuilder("Bienvendio al horariode atencion a cliente");
			mensaje.append(", atendemos desde las ");
			mensaje.append(apertura);
			mensaje.append("hrs ");
			mensaje.append(" hasta las ");
			mensaje.append(cierre);
			mensaje.append("hrs");
			mensaje.append(". Gracias por su visita.");
			
			//Agregamos el mensaje al respesta para añadirlo a la vista
			request.setAttribute("mensaje", mensaje.toString());
			return true;
		}
		//Esta fuera del horario
		response.sendRedirect(request.getContextPath().concat("/control/cerrado"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//Recogemos una vez finalizado correctamente el método handler el mensaje y lo añadimos a la vista
		String mensaje = (String) request.getAttribute("mensaje");
		
		//Para evitar el error NullPointerException cuando el interceptor es lanzado por algún regurso, se puede comprobar los
		//parámetros "modelAndView" y "handler"
		if(modelAndView!=null && handler instanceof HandlerMethod) {
			modelAndView.addObject("horario",mensaje);
		}
	}
}
