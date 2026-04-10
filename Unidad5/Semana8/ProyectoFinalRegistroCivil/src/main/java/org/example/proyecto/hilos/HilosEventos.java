package org.example.proyecto.hilos;

import org.example.proyecto.modelo.Persona;
import org.example.proyecto.modelo.Evento;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de la ejecución concurrente de eventos mediante hilos.
 * <p>
 * Permite procesar múltiples eventos de una persona en paralelo,
 * asegurando sincronización durante la ejecución de operaciones críticas.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class HilosEventos {

    /**
     * Registra una lista de eventos asociados a una persona utilizando hilos.
     * <p>
     * Cada evento se procesa en un hilo independiente para simular concurrencia.
     * Se asegura la sincronización en la salida estándar para evitar conflictos
     * en la impresión de datos.
     * </p>
     *
     * @param persona persona a la que se asociarán los eventos
     * @param eventos lista de eventos a procesar
     */
    public void registrarEventos(Persona persona, List<Evento> eventos) {

        List<Thread> hilos = new ArrayList<>();

        for (Evento evento : eventos) {
            Thread hilo = new Thread(() -> {
                try {
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

        for (Thread h : hilos) h.start();

        for (Thread h : hilos) {
            try {
                h.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error en ejecución de hilo");
            }
        }

        for (Evento e : eventos) {
            persona.agregarEvento(e);
        }
    }
}