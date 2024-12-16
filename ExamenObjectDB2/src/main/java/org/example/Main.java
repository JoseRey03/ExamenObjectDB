package org.example;

import org.example.models.Comentario;
import org.example.models.Usuario;
import org.example.service.DataService;

import javax.persistence.*;

public class Main {

    public static void main(String[] args) {
        DataService dataService = new DataService();

        // 1. Registrar usuarios
        dataService.registrarUsuario("romero@gmail.com", "Francisco Romero");
        dataService.registrarUsuario("pablo@gmail.com", "Pablo Rodrigez");

        // 2. Añadir comentarios a usuarios
        dataService.anadirComentarioAUsuario("romero@gmail.com", "Buena pelicula", 10);
        dataService.anadirComentarioAUsuario("romero@gmail.com", "Esta bien", 7);
        dataService.anadirComentarioAUsuario("pablo@gmail.com", "Locuron", 10);

        // 3. Listar comentarios de un usuario específico
        dataService.listarComentariosDeUsuario("romero@gmail.com");

        // 4. Listar usuarios con comentarios de valoración máxima
        dataService.listarUsuariosConComentariosValoracionMaxima();

        // Cerrar la conexión
        dataService.cerrarConexion();
    }
}
