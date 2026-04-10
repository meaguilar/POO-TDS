package org.example.proyecto.dao;

import org.example.proyecto.modelo.Persona;
import org.example.proyecto.persistencia.ArchivoTexto;

import java.util.List;

/**
 * Implementación de la interfaz PersonaDAO.
 * <p>
 * Maneja la persistencia de objetos Persona utilizando archivos de texto,
 * permitiendo guardar y consultar personas por su identificador.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class PersonaDAOImpl implements PersonaDAO {

    /** Clase encargada del manejo de archivos de texto */
    private ArchivoTexto archivo;

    /**
     * Constructor de la implementación DAO.
     * Inicializa el archivo donde se almacenan las personas.
     */
    public PersonaDAOImpl() {
        this.archivo = new ArchivoTexto("data/personas.txt");
    }

    /**
     * Guarda una persona en el archivo de texto.
     *
     * @param persona objeto Persona que se desea almacenar
     */
    @Override
    public void guardar(Persona persona) {
        String linea = persona.getId() + "," +
                persona.getNombre() + "," +
                persona.getApellido();

        archivo.escribir(linea);
    }

    /**
     * Busca una persona por su identificador en el archivo.
     *
     * @param id identificador de la persona
     * @return objeto Persona si se encuentra, null en caso contrario
     */
    @Override
    public Persona obtenerPorId(int id) {
        List<String> lineas = archivo.leer();

        for (String linea : lineas) {
            String[] datos = linea.split(",");

            int idArchivo = Integer.parseInt(datos[0]);

            if (idArchivo == id) {
                Persona p = new Persona();
                p.setId(idArchivo);
                p.setNombre(datos[1]);
                p.setApellido(datos[2]);
                return p;
            }
        }

        return null;
    }
}