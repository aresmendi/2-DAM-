package com.iesserpis.agenda.service;
import com.iesserpis.agenda.entity.Direccion;
import com.iesserpis.agenda.entity.Persona;
import com.iesserpis.agenda.repository.PersonaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class PersonaService {
    private final EntityManagerFactory emf;
    public PersonaService(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public Persona crearPersona(String nombre, String apellidos, String email, String telefono) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Persona persona = new Persona(nombre, apellidos, email, telefono);
        try {
            tx.begin();
            PersonaRepository repo = new PersonaRepository(em);
            repo.guardar(persona);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }

        return persona;
    }
    public List<Persona> listarTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            PersonaRepository repo = new PersonaRepository(em);
            return repo.buscarTodas();
        } finally {
            em.close();
        }
    }


    // AHORA AÑADIMOS UN NUEVO METODO: crear Persona + Direcciones asociadas
    public Persona crearPersonaConDirecciones(Persona persona, Direccion... direcciones) {

        // Primero añadimos las direcciones a la persona usando el helper!!!
        for (Direccion d : direcciones) {
            persona.addDireccion(d);// fijaos que usamos este metodo que ya teníamos para sincronizar ambos lados de la relación
        }

        // A continuacion, guardamos igual que anterioment en crearPersona
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            PersonaRepository repo = new PersonaRepository(em);
            repo.guardar(persona);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }

        return persona;
    }
}
