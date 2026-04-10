package org.example.proyecto.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una persona dentro del sistema Registro Civil.
 * <p>
 * Almacena información básica como id, nombre y apellido,
 * además de una lista de eventos asociados a la persona.
 * </p>
 */
public class Persona {

    /** Identificador único de la persona */
    private int id;

    /** Nombre de la persona */
    private String nombre;

    /** Apellido de la persona */
    private String apellido;

    /** Lista de eventos asociados a la persona */
    private List<Evento> eventos;

    /**
     * Constructor por defecto.
     * Inicializa la lista de eventos.
     */
    public Persona() {
        this.eventos = new ArrayList<>();
    }

    /**
     * Constructor parametrizado.
     *
     * @param id identificador de la persona
     * @param nombre nombre de la persona
     * @param apellido apellido de la persona
     */
    public Persona(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.eventos = new ArrayList<>();
    }

   // Getter y Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    /**
     * Retorna la misma instancia de la persona.
     *
     * @return objeto Persona actual
     */
    public Persona obtenerDatos() {
        return this;
    }

    /**
     * Agrega un evento a la lista de eventos de la persona.
     *
     * @param evento evento a asociar
     */
    public void agregarEvento(Evento evento) {
        this.eventos.add(evento);
    }
}