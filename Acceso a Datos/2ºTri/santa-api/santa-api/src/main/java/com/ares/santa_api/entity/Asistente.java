package com.ares.santa_api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Asistente {
    @Id
    private String id;

    @OneToMany(targetEntity = Carta.class, fetch = FetchType.LAZY, mappedBy = "asistente")
    private List<Carta> cartas;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }
    public List<Carta> getCartas() {
        return cartas;
    }
}
