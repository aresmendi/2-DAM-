package com.iesserpis.agenda.repository;

import com.iesserpis.agenda.entity.Persona;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PersonaRepository {

    private final EntityManager em;

    public PersonaRepository(EntityManager em) {
        this.em = em;
    }

    public void guardar(Persona persona) {
        em.persist(persona);
    }

    public Persona buscarPorId(Integer id) {
        return em.find(Persona.class, id);
    }

    public List<Persona> buscarTodas() {
        return em.createQuery("SELECT p FROM Persona p", Persona.class)
                .getResultList();
    }

    public void eliminar(Persona persona) {
        Persona managed = persona;
        if (!em.contains(persona)) {
            managed = em.merge(persona);
        }
        em.remove(managed);
    }
}
