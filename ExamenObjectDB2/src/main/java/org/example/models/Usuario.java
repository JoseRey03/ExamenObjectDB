package org.example.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    private String correo; // Clave primaria

    private String nombre;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();

    // Constructor vacío
    public Usuario() {}

    // Constructor con parámetros
    public Usuario(String correo, String nombre) {
        this.correo = correo;
        this.nombre = nombre;
    }

    // Método para añadir comentario
    public void anadirComentario(Comentario comentario) {
        comentario.setUsuario(this);
        this.comentarios.add(comentario);
    }

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }
}
