package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

public class NombreMayusculaEditor extends PropertyEditorSupport{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(text.toUpperCase().trim()); //Convierte el valor a maysucla y elimina espacios iniailes y finales
	}

}
