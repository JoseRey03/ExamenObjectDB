package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDBUtil {

    // Se declara un campo estático de tipo EntityManagerFactory
    private static EntityManagerFactory emf;

    // Bloque estático, que se ejecuta una sola vez cuando la clase es cargada
    static{
        // Aquí se inicializa el EntityManagerFactory usando la configuración de "data.odb"
        emf = Persistence.createEntityManagerFactory("data.odb");
    }

    // Método público que retorna la instancia de EntityManagerFactory
    public static EntityManagerFactory getEntityManagerFactory(){
        // Retorna la instancia previamente creada
        return emf;
    }
}

