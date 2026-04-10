package org.example.proyecto.dao;

import org.example.proyecto.modelo.Persona;

/**
 * Interfaz DAO para la gestión de personas en el sistema Registro Civil.
 * <p>
 * Define las operaciones básicas de persistencia relacionadas con
 * la entidad Persona, como guardar y consultar por identificador.
 * </p>
 *
 * @author
 * @version 1.0
 */
public interface PersonaDAO {

    /**
     * Guarda una persona en el sistema de persistencia.
     *
     * @param persona objeto Persona que se desea almacenar
     */
    void guardar(Persona persona);

    /**
     * Obtiene una persona a partir de su identificador.
     *
     * @param id identificador único de la persona
     * @return objeto Persona si es encontrada, o null si no existe
     */
    Persona obtenerPorId(int id);
}