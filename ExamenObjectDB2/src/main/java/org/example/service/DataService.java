package org.example.service;

import org.example.models.Comentario;
import org.example.models.Usuario;

import javax.persistence.*;
import java.util.List;

public class DataService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public DataService() {
        this.emf = Persistence.createEntityManagerFactory("objectdb:db/usuarios.odb");
        this.em = emf.createEntityManager();
    }

    // 1. Registrar un nuevo usuario en la plataforma
    public void registrarUsuario(String correo, String nombre) {
        em.getTransaction().begin();
        Usuario usuario = new Usuario(correo, nombre);
        em.persist(usuario);
        em.getTransaction().commit();
        System.out.println("Usuario registrado: " + nombre);
    }

    // 2. Listar los comentarios de un usuario específico
    public void listarComentariosDeUsuario(String correo) {
        TypedQuery<Comentario> query = em.createQuery(
                "SELECT c FROM Comentario c WHERE c.usuario.correo = :correo", Comentario.class);
        query.setParameter("correo", correo);
        List<Comentario> comentarios = query.getResultList();

        System.out.println("Comentarios del usuario con correo " + correo + ":");
        for (Comentario c : comentarios) {
            System.out.println("- " + c.getContenido() + " (Valoración: " + c.getValoracion() + ")");
        }
    }

    // 3. Añadir un comentario a la plataforma
    public void anadirComentarioAUsuario(String correo, String contenido, int valoracion) {
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, correo);
        if (usuario != null) {
            Comentario comentario = new Comentario(contenido, valoracion);
            usuario.anadirComentario(comentario);
            em.persist(comentario);
            em.getTransaction().commit();
            System.out.println("Comentario añadido al usuario: " + correo);
        } else {
            System.out.println("Usuario no encontrado.");
            em.getTransaction().rollback();
        }
    }

    // 4. Listar los usuarios que han realizado comentarios con la valoración máxima
    public void listarUsuariosConComentariosValoracionMaxima() {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT DISTINCT c.usuario FROM Comentario c WHERE c.valoracion = 10", Usuario.class);
        List<Usuario> usuarios = query.getResultList();

        System.out.println("Usuarios con comentarios de valoración máxima (10):");
        for (Usuario u : usuarios) {
            System.out.println("- " + u.getCorreo());
        }
    }

    // Cerrar EntityManager
    public void cerrarConexion() {
        em.close();
        emf.close();
    }
}
