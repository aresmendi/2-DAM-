package com.iesserpis.agenda.app;

import com.iesserpis.agenda.entity.Direccion;
import com.iesserpis.agenda.entity.Persona;
import com.iesserpis.agenda.service.PersonaService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AgendaJPA {
    public static void main(String[] args) {

        // Tenemos que crear la factoria de EntityManagers
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        PersonaService service = new PersonaService(emf);

        // CÓDGIO PARA GUARDAR UNA PERSONA
        System.out.println("GUARDAR PERSONA");
        Persona persona1 = service.crearPersona(
                "Maripuri4",
                "Martínez",
                "mpuri4@gmail.com",
                "123456789"
        );
        System.out.println("La persona "+ persona1 + "ha sido guardada correctament: ");


        // ******* ATENCIÓN!!! a partir de aqui es la parte nueva de código para insertar una persona con direcciones

        System.out.println("\nGUARDAR PERSONA CON DIRECCIONES");
        System.out.println("\nGUARDAR PERSONA CON DIRECCIONES");

        // Crear persona
        Persona p2 = new Persona();
        p2.setNombre("Miguel");
        p2.setApellidos("De Cervantes");
        p2.setEmail("Miguel@gmail.com");
        p2.setTelefono("654123789");

        // Crear direcciones (sin repetir nombres d1/d2)
        Direccion dir1 = new Direccion(
                "Calle Unamuno,20",
                "Valencia",
                "46001",
                "España"
        );

        Direccion dir2 = new Direccion(
                "Av. de las letras, 10",
                "Valencia",
                "46023",
                "España"
        );

        // Ahora asociamos a la persona y guardamos usando el service
        service.crearPersonaConDirecciones(p2, dir1, dir2);

        System.out.println("Persona con direcciones guardada correctamente.");

        // listamos de nuevo para asegurarnos de que se ha insertado todo ok

        System.out.println("\nLISTADO FINAL DE PERSONAS");
        for (Persona pers : service.listarTodas()) {
            System.out.println(pers);
        }

        // Cierre de recursos al final del todo el codigo!!
        emf.close();
    }
    }



