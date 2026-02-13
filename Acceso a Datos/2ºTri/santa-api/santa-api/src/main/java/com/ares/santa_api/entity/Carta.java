package com.ares.santa_api.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Carta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaEntrega;

    @ManyToOne(targetEntity = Asistente.class)
    private Asistente asistente;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Asistente getAsistente() {
        return asistente;
    }
    public void setAsistente(Asistente asistente) {
        this.asistente = asistente;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }
    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}
