package com.iesserpis.agenda.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 80)
    private String apellidos;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(length = 20)
    private String telefono;

    @OneToMany(
            mappedBy = "persona", //esto está explicado en la teoría
            cascade = CascadeType.ALL, // ver la tabla excel que hay en la guía 2 apartado 3.1
            orphanRemoval = true, // ver la tabla excel que hay en la guía 2 apartado 3.1
            fetch = FetchType.LAZY  // para optimizar velocidad y no sobrecargar, elegimos LAZY y no EAGER
    )
    private List<Direccion> direcciones = new ArrayList<>(); //Así podremos llegar a todas las direcciones de una persona

    public void addDireccion(Direccion direccion) { // esto es un metodo Helper, ver la guía 2 apartado 3.2
        direcciones.add(direccion);
        direccion.setPersona(this);
    }

    public void removeDireccion(Direccion direccion) {// esto es otro metodo Helper, ver la guía 2 apartado 3.2
        direcciones.remove(direccion);
        direccion.setPersona(null);
    }


    public Persona() {
    }

    public Persona(String nombre, String apellidos, String email, String telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
