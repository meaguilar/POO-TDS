package org.example.proyecto.patrones.strategy;

import org.example.proyecto.modelo.Evento;
import org.example.proyecto.modelo.Nacimiento;

/**
 * Implementación del generador de actas para eventos de nacimiento.
 * <p>
 * Forma parte del patrón de diseño Strategy, encargado de generar
 * el acta específica para el evento de tipo {@link Nacimiento}.
 * </p>
 */
public class GeneradorActaNacimiento implements GeneradorActa {

    /**
     * Genera e imprime el acta de un evento de nacimiento.
     * <p>
     * Muestra la información básica del evento y, si el evento es
     * de tipo {@link Nacimiento}, incluye datos adicionales como
     * hora, fecha y lugar de nacimiento.
     * </p>
     *
     * @param evento evento sobre el cual se generará el acta
     */
    @Override
    public void generarActa(Evento evento) {

        String acta = "\n"
                + "Generando acta de nacimiento...\n"
                + "\nInformación básica del acta:\n"
                + "Folio: " + evento.getFolio() + "\n"
                + "Número de acta: " + evento.getNumActa() + "\n"
                + "Fecha de registro: " + evento.getFechaReg() + "\n";

        if (evento instanceof Nacimiento n) {
            acta += "Hora de nacimiento: " + n.getHora() + "\n"
                    + "Fecha de nacimiento: " + n.getFecha() + "\n"
                    + "Lugar de nacimiento: " + n.getLugar() + "\n";
        }

        System.out.println(acta);
    }
}