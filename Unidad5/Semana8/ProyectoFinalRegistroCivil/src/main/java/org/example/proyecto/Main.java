package org.example.proyecto;

import org.example.proyecto.dao.EventoDAO;
import org.example.proyecto.dao.EventoDAOImpl;
import org.example.proyecto.dao.PersonaDAO;
import org.example.proyecto.dao.PersonaDAOImpl;
import org.example.proyecto.hilos.HilosEventos;
import org.example.proyecto.modelo.Evento;
import org.example.proyecto.modelo.Persona;
import org.example.proyecto.ui.EntradaDatos;

import java.util.List;
import java.util.Scanner;
/**
 * Clase principal del sistema Registro Civil.
 * <p>
 * Coordina la ejecución de la aplicación, permitiendo la entrada de datos,
 * procesamiento de eventos mediante hilos y persistencia en archivos.
 * </p>
 */
public class Main {
    /**
     * Punto de entrada de la aplicación.
     * <p>
     * Se encarga de:
     * <ul>
     *     <li>Capturar datos desde consola</li>
     *     <li>Crear persona y eventos</li>
     *     <li>Procesar eventos en paralelo usando hilos</li>
     *     <li>Persistir información en archivos mediante DAO</li>
     * </ul>
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Instanciar DAOs
        PersonaDAO personaDAO = new PersonaDAOImpl();
        EventoDAO eventoDAO = new EventoDAOImpl();

        // Leer datos desde consola
        EntradaDatos entrada = new EntradaDatos(sc);
        Persona persona = entrada.leerPersona();
        List<Evento> eventos = entrada.leerEventos(persona);

        // Registrar eventos en paralelo usando org.example.proyecto.hilos
        HilosEventos hilos = new HilosEventos();
        hilos.registrarEventos(persona, eventos);

        // Guardar persona y eventos en archivos usando DAO
        // personas.txt
        personaDAO.guardar(persona);
        for (Evento e : persona.getEventos()) {
            // eventos.txt
            eventoDAO.guardar(e);
        }

        System.out.println("\nDatos guardados correctamente en archivos.");
        sc.close();
    }
}