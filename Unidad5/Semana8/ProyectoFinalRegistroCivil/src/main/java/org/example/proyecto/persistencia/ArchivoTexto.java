package org.example.proyecto.persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada del manejo de archivos de texto planos.
 * <p>
 * Permite realizar operaciones básicas de lectura y escritura
 * sobre archivos, utilizados como mecanismo de persistencia simple.
 * </p>
 */
public class ArchivoTexto {

    /** Nombre del archivo donde se almacenan los datos */
    private String nombreArchivo;

    /**
     * Constructor de la clase ArchivoTexto.
     * <p>
     * Crea el archivo si no existe previamente.
     * </p>
     *
     * @param nombreArchivo ruta o nombre del archivo de texto
     */
    public ArchivoTexto(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;

        try {
            File f = new File(nombreArchivo);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Escribe una línea en el archivo de texto.
     * <p>
     * La información se agrega al final del archivo sin sobrescribir el contenido existente.
     * </p>
     *
     * @param linea texto que se desea escribir en el archivo
     */
    public void escribir(String linea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee todas las líneas del archivo de texto.
     *
     * @return lista de cadenas con el contenido del archivo
     */
    public List<String> leer() {
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineas;
    }
}