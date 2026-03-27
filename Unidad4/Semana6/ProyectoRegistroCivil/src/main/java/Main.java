import dao.EventoDAO;
import dao.EventoDAOImpl;
import dao.PersonaDAO;
import dao.PersonaDAOImpl;
import hilos.HilosEventos;
import modelo.Evento;
import modelo.Persona;
import ui.EntradaDatos;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Instanciar DAOs
        PersonaDAO personaDAO = new PersonaDAOImpl();
        EventoDAO eventoDAO = new EventoDAOImpl();

        // Leer datos desde consola
        EntradaDatos entrada = new EntradaDatos(sc);
        Persona persona = entrada.leerPersona();
        List<Evento> eventos = entrada.leerEventos(persona);

        // Registrar eventos en paralelo usando hilos
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