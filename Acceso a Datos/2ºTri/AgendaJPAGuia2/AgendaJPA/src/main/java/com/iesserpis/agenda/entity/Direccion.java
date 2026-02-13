package com.iesserpis.agenda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;
    private String calle;
    private String ciudad;
    private String cp;
    private String pais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")   // esta es la FK!!!
    private Persona persona;

    // a partir de aquí, insertamos constructores, getters, setters y toString
    public Direccion() {}

    public Direccion(String calle, String ciudad, String cp, String pais) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.cp = cp;
        this.pais = pais;
    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "idDireccion=" + idDireccion +
                ", calle='" + calle + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", cp='" + cp + '\'' +
                ", pais='" + pais + '\'' +
                ", persona=" + persona +
                '}';
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}