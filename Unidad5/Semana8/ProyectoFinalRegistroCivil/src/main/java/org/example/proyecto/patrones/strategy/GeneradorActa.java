package org.example.proyecto.patrones.strategy;

import org.example.proyecto.modelo.Evento;

/**
 * Interfaz que define el comportamiento para la generación de actas
 * utilizando el patrón de diseño Strategy.
 * <p>
 * Permite implementar diferentes formas de generar actas según el tipo de evento.
 * </p>
 */
public interface GeneradorActa {

    /**
     * Genera el acta correspondiente a un evento específico.
     *
     * @param evento evento sobre el cual se generará el acta
     */
    void generarActa(Evento evento);
}