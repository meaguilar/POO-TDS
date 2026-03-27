package hilos;

import modelo.Persona;
import modelo.Evento;

import java.util.ArrayList;
import java.util.List;

public class HilosEventos {

    // Registra los eventos de una persona usando hilos
    public void registrarEventos(Persona persona, List<Evento> eventos) {

        List<Thread> hilos = new ArrayList<>();

        // Crear hilos para cada evento
        for (Evento evento : eventos) {
            Thread hilo = new Thread(() -> {
                try {
                    // Registrar evento y generar acta de forma segura
                    synchronized (System.out) {
                        evento.registrarEvento();
                        evento.imprimir();
                    }
                } catch (Exception e) {
                    System.out.println("Error procesando evento: " + e.getMessage());
                }
            });
            hilos.add(hilo);
        }

        // Iniciar hilos
        for (Thread h : hilos) h.start();

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
    }
}