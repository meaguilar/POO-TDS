package org.example.proyecto.modelo;

import org.example.proyecto.patrones.strategy.GeneradorActa;

import java.time.LocalDate;

/**
 * Clase abstracta que representa un evento del sistema Registro Civil.
 * <p>
 * Define la estructura base de cualquier tipo de evento (como nacimiento),
 * incluyendo datos comunes como folio, número de acta, fecha de registro
 * y la persona registradora. Implementa la interfaz {@link Imprimible}.
 * </p>
 */
public abstract class Evento implements Imprimible {

    /** Identificador único del evento */
    protected String folio;

    /** Número del acta asociada al evento */
    protected int numActa;

    /** Fecha en la que se registra el evento */
    protected LocalDate fechaReg;

    /** Persona que registra el evento */
    protected Persona registrador;

    /** Estrategia para generar el acta (patrón Strategy) */
    protected GeneradorActa estrategia;

    /**
     * Constructor parametrizado de Evento.
     *
     * @param folio identificador del evento
     * @param numActa número de acta
     * @param fechaReg fecha de registro del evento
     * @param registrador persona que registra el evento
     */
    public Evento(String folio, int numActa, LocalDate fechaReg, Persona registrador) {
        this.folio = folio;
        this.numActa = numActa;
        this.fechaReg = fechaReg;
        this.registrador = registrador;
    }

    // =========================
    // Getters y Setters
    // =========================

    public String getFolio() {
        return folio;
    }

    public int getNumActa() {
        return numActa;
    }

    public LocalDate getFechaReg() {
        return fechaReg;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setNumActa(int numActa) {
        this.numActa = numActa;
    }

    public void setFechaReg(LocalDate fechaReg) {
        this.fechaReg = fechaReg;
    }

    public void setRegistrador(Persona registrador) {
        this.registrador = registrador;
    }

    public Persona getRegistrador() {
        return registrador;
    }

    /**
     * Imprime la información básica del evento.
     * Incluye datos del folio, número de acta, fecha y registrador.
     */
    @Override
    public void imprimir() {
        System.out.println("Información básica del acta:");
        System.out.println("Folio: " + getFolio());
        System.out.println("Número de acta: " + getNumActa());
        System.out.println("Fecha de registro: " + getFechaReg());

        if (registrador != null) {
            System.out.println("Registrador: " +
                    registrador.getNombre() + " " +
                    registrador.getApellido());
        }
    }

    /**
     * Asigna la estrategia de generación de actas (Patrón Strategy).
     *
     * @param estrategia objeto que define cómo se genera el acta
     */
    public void setEstrategia(GeneradorActa estrategia) {
        this.estrategia = estrategia;
    }

    /**
     * Ejecuta la generación del acta utilizando la estrategia asignada.
     * Si no hay estrategia definida, no realiza ninguna acción.
     */
    public void registrarEvento() {
        if (estrategia != null) {
            estrategia.generarActa(this);
        }
    }
}