package org.example.proyecto.modelo;

/**
 * Interfaz que define la capacidad de impresión de un objeto.
 * <p>
 * Las clases que implementen esta interfaz deben proporcionar
 * su propia forma de mostrar información en consola o salida estándar.
 * </p>
 */
public interface Imprimible {

    /**
     * Imprime la información del objeto.
     * <p>
     * Cada clase implementará este método según su estructura de datos.
     * </p>
     */
    void imprimir();
}