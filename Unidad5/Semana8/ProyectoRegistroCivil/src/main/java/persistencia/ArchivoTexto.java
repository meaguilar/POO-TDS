package persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Clase para manejo de archivos planos (lectura/escritura)
public class ArchivoTexto {
    private String nombreArchivo;

    public ArchivoTexto(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        // Crear archivo si no existe
        try {
            File f = new File(nombreArchivo);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Escribir línea al archivo
    public void escribir(String linea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Leer todas las líneas del archivo
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