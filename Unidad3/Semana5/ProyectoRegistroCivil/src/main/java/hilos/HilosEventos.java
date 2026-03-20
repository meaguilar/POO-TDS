package hilos;

import modelo.Persona;
import modelo.Evento;

import java.util.ArrayList;
import java.util.List;

public class HilosEventos {

    public void registrarEventos(Persona persona, List<Evento> eventos) {

        List<Thread> hilos = new ArrayList<>();

        // Crear hilos
        for (Evento evento : eventos) {

            Thread hilo = new Thread(() -> {
                try {
                    // Registrar evento y generar acta de manera segura
                    synchronized(System.out) {
                        evento.registrarEvento();
                    }
                } catch (Exception e) {
                    System.out.println("Error procesando evento: " + e.getMessage());
                }
            });

            hilos.add(hilo);
        }

        // Iniciar hilos
        for (Thread h : hilos) {
            h.start();
        }

        // Esperar que terminen
        for (Thread h : hilos) {
            try {
                h.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error en ejecución de hilo");
            }
        }

        // Asociar eventos a la persona
        for (Evento e : eventos) {
            persona.agregarEvento(e);
        }

        // Resumen final
        System.out.println("\n=========== RESUMEN ===========");
        System.out.println("Persona: " + persona.getNombre() + " " + persona.getApellido());
        System.out.println("Eventos registrados: " + persona.getEventos().size());
    }
}