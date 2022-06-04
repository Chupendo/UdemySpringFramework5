package com.example.springbootdatajpaimproved.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id //Indica que este el atributo es la primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Autoincremento para MySQLQ
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)//Autoincremento pàra SQL, PostgreSQL
    private Long id;

    @NotEmpty
    @Column(name="nombre_cliente",nullable = false,length =40)
    @Size(min=2,max=10)
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Column(name="create_at")
    @Temporal(TemporalType.DATE)//Formato de la fecha
    @DateTimeFormat(pattern="yyy-MM-dd") //Validaidon de formato para la fecha
    private Date createAt;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the createAt
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt the createAt to set
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * Agrega la fecha de creacion antes de realizar la persistencia
     * de forma automática
     */
    @PrePersist
    public void prePersit() {
        System.out.println("fecha introduicda en el formulario");
        //this.createAt = new Date();
    }
}
