package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.form.app.services.PaisServiceImp;

@Component
public class GetPaisPropertyEditor extends PropertyEditorSupport {
	@Autowired
	private PaisServiceImp service;
	
	@Override
	public void setAsText(String idString) throws IllegalArgumentException {
		if(idString != null && idString.length()>0) {
			try {
			Integer id = Integer.parseInt(idString);
			this.setValue(service.obtenerPais(id));
			}catch(NumberFormatException e) {
				this.setValue(null);
			}
		}else {
			this.setValue(null);
		}
	}
}
