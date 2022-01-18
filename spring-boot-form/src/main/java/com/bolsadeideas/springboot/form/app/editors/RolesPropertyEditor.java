package com.bolsadeideas.springboot.form.app.editors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.form.app.services.IRoleService;

@Component
public class RolesPropertyEditor extends PropertiesEditor {

	@Autowired
	private IRoleService service;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Integer id = null;
		try {
			//Convertirmos el campo "id" de string a entero o integer
			id = Integer.parseInt(text);
			setValue(service.obtenerPorId(id));
		}catch (NumberFormatException e) {
			// Si falla
			setValue(null);
		}
	}
}
