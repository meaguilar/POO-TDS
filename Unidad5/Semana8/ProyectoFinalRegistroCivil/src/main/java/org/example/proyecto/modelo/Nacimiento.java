package org.example.proyecto.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que representa un evento de nacimiento.
 * <p>
 * Hereda de {@link Evento} y añade información específica como
 * la hora, fecha de nacimiento y lugar donde ocurrió el evento.
 * </p>
 */
public class Nacimiento extends Evento {

    /** Hora en la que ocurrió el nacimiento */
    private LocalTime hora;

    /** Fecha específica del nacimiento */
    private LocalDate fecha;

    /** Lugar donde ocurrió el nacimiento */
    private String lugar;

    /**
     * Constructor de la clase Nacimiento.
     *
     * @param folio identificador del evento
     * @param numActa número de acta
     * @param fechaReg fecha de registro del evento
     * @param registrador persona que registra el evento
     * @param hora hora del nacimiento
     * @param fecha fecha del nacimiento
     * @param lugar lugar del nacimiento
     */
    public Nacimiento(String folio, int numActa, LocalDate fechaReg, Persona registrador,
                      LocalTime hora, LocalDate fecha, String lugar) {

        super(folio, numActa, fechaReg, registrador);
        this.hora = hora;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }
}