package dao;

import modelo.Persona;
import persistencia.ArchivoTexto;

import java.util.List;

public class PersonaDAOImpl implements PersonaDAO {

    private ArchivoTexto archivo;

    public PersonaDAOImpl() {
        this.archivo = new ArchivoTexto("data/personas.txt");
    }

    @Override
    public void guardar(Persona persona) {
        // Guardamos id, nombre, apellido
        String linea = persona.getId() + "," + persona.getNombre() + "," + persona.getApellido();
        archivo.escribir(linea);
    }

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
        // No encontrado
        return null;
    }
}