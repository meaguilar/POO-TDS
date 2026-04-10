package org.example.proyecto.dao;

import org.example.proyecto.modelo.Evento;
import java.util.List;

/**
 * Interfaz DAO para la gestión de eventos en el sistema Registro Civil.
 * <p>
 * Define las operaciones básicas de persistencia relacionadas con
 * los eventos, como guardar y listar eventos asociados a una persona.
 * </p>
 *
 * @author
 * @version 1.0
 */
public interface EventoDAO {

    /**
     * Guarda un evento en el sistema de persistencia.
     *
     * @param evento objeto Evento que se desea almacenar
     */
    void guardar(Evento evento);

    /**
     * Obtiene la lista de eventos asociados a una persona específica.
     *
     * @param idPersona identificador de la persona
     * @return lista de eventos registrados para esa persona
     */
    List<Evento> listarPorPersona(int idPersona);
}