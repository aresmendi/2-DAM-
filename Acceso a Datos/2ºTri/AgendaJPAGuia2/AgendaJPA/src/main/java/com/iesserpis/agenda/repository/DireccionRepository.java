package com.iesserpis.agenda.repository;
import com.iesserpis.agenda.entity.Direccion;
import jakarta.persistence.EntityManager;
import java.util.List;

//no es obligatorio crear esta clase, tal y como explico en la guia, pero sí recomendable en este punto del aprendizaje
public class DireccionRepository {

    private final EntityManager em;

    public DireccionRepository(EntityManager em) {
        this.em = em;
    }
    public void guardar(Direccion direccion) {
        em.persist(direccion);
      }

    public Direccion buscarPorId(Long id) {
        return em.find(Direccion.class, id);
    }

    public void actualizar(Direccion direccion) {
        em.merge(direccion);
    }

    public void eliminar(Direccion direccion) {
        em.remove(direccion);
    }

    public List<Direccion> buscarTodas() {
        return em.createQuery("SELECT d FROM Direccion d", Direccion.class).getResultList();
    }
}
