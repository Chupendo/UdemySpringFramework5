package com.bolsadeideas.springboot.form.app.model.domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.bolsadeideas.springboot.form.app.validation.IdentenficiadorRegex;
import com.bolsadeideas.springboot.form.app.validation.Requerido;



public class Usuario {

	//@Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d][-][A-Z]{1}") //Comentamos para que no se registre el error dos veces si se usa también en la clase validadora personalizada
	@IdentenficiadorRegex
	private String identificador;
	
	//Comentamos la anotacion para no validar dos veces al validar el campo en nuestra clase UsuarioValidador
	//@NotEmpty(message = "el nombre no puede ser vacio") //message es remplazado por el NotEmpty de messages.properties
	private String nombre;
	
	//@NotEmpty
	@Requerido
	private String apellido;
	
	//@NotEmpty //Valida que nos sea vacio
	@NotBlank //Valida que no sea vacio o no tenga espacios en blanco
	@Size(min=3, max=8)
	private String username;
	
	@NotEmpty //Valida que no sea vacio "requerido"
	private String password;
	
	@NotEmpty
	@Email(message = "correo con formato incorrecto")
	private String email;
	
	//Valida que no la propiedad cuenta no sea nulo y este entre 5 y 20
	@NotNull
	@Min(5)  //Mínimo 5
	@Max(20) //Maximo 20
	private Integer cuenta;
	
	//Validado y Formateado con “SimpleDateFormat” (CostumerDateEditor) mediante un InitBinder
	@NotNull //Comprueba que el campo no sea vacío
	//@Size(min=3, max=8) //No soportada para objetos de tipo Date
	//@DateTimeFormat(pattern = "yyy/MM/dd HH:mm:ss") //Patron de formato para la fecha con input tipo text
	//@DateTimeFormat(pattern = "yyy-MM-dd") //Patron de formato para la fecha con input tipo date: ISO Date Format yyyy-MM-dd — for example,"2000-10-31".
	//@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //Patron de formato para la fecha con input tipo datetime-local: ISO Date Format yyyy-MM-ddTHH:mm:ss
	//@Past //Valida si la fecha recibida es pasada al día presente
	@Future //Valida si la fecha recibida es mayor al día presente
	private Date birthday; //Atributo para almacenar de una Fecha, objeto de java.util
//	
//	//Valida que no la propiedad cuenta almenos tenga un dígito
//	@Min(1) //Mínimo 1 digito
//	private int cuenta;
//	
	//@NotEmpty //Comprobamos que no sea vacio el string. Nota: si fuera un objeto sería con NotNull
	//private String pais;
	
	@NotNull
	//@Valid //No es necesario al obtener el objeto completo de tipo Pais
	private Pais pais;
	
	@NotNull
	private List<Role> roles;
	
	//Atributo opcion para checkbox de tipo booleano
	//Si es true el usuario esta habilitado
	private Boolean habilitar;
	
	@NotEmpty
	private String genero;
	
		
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Boolean getHabilitar() {
		return habilitar;
	}

	public void setHabilitar(Boolean habilitar) {
		this.habilitar = habilitar;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/* public String getPais() {	return pais; } */
	public Pais getPais() {
		return pais;
	}
	
	/*public void setPais(String pais) { this.pais = pais; }*/
	
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getCuenta() {
		return cuenta;
	}
	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cuenta, email, identificador, password, username);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		return Objects.equals(cuenta, other.cuenta) && Objects.equals(email, other.email)
				&& Objects.equals(identificador, other.identificador) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "Usuario [identificador=" + identificador + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", username=" + username + ", password=" + password + ", email=" + email + ", cuenta=" + cuenta
				+ ", birthday=" + birthday + ", pais=" + pais + ", roles=" + roles + ", habilitar=" + habilitar
				+ ", genero=" + genero + "]";
	}
	
	
	
	
}
