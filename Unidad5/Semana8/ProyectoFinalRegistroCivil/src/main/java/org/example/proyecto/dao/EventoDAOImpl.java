package org.example.proyecto.dao;

import org.example.proyecto.modelo.Evento;
import org.example.proyecto.modelo.Nacimiento;
import org.example.proyecto.modelo.Persona;
import org.example.proyecto.persistencia.ArchivoTexto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz EventoDAO.
 * <p>
 * Maneja la persistencia de eventos en archivos de texto,
 * permitiendo guardar y listar eventos asociados a una persona.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class EventoDAOImpl implements EventoDAO {

    /** Clase encargada del manejo de archivos de texto */
    private ArchivoTexto archivo;

    /**
     * Constructor de la implementación DAO.
     * Inicializa el archivo donde se almacenan los eventos.
     */
    public EventoDAOImpl() {
        this.archivo = new ArchivoTexto("data/eventos.txt");
    }

    /**
     * Guarda un evento en el archivo de texto.
     * <p>
     * Si el evento es de tipo Nacimiento, también se almacenan
     * datos adicionales como hora, fecha y lugar.
     * </p>
     *
     * @param evento objeto Evento a guardar
     */
    @Override
    public void guardar(Evento evento) {
        String linea = evento.getFolio() + "," +
                evento.getNumActa() + "," +
                evento.getRegistrador().getId() + "," +
                evento.getFechaReg();

        if (evento instanceof Nacimiento n) {
            linea += "," + n.getHora() + "," + n.getFecha() + "," + n.getLugar();
        }

        archivo.escribir(linea);
    }

    /**
     * Lista los eventos registrados para una persona específica.
     *
     * @param idPersona identificador de la persona
     * @return lista de eventos asociados a la persona
     */
    @Override
    public List<Evento> listarPorPersona(int idPersona) {
        List<Evento> eventos = new ArrayList<>();
        List<String> lineas = archivo.leer();

        for (String linea : lineas) {
            String[] datos = linea.split(",");

            int idRegistrador = Integer.parseInt(datos[2]);

            if (idRegistrador == idPersona) {

                String folio = datos[0];
                int numActa = Integer.parseInt(datos[1]);
                LocalDate fechaReg = LocalDate.parse(datos[3]);

                Persona registrador = new Persona();
                registrador.setId(idRegistrador);

                Evento e;

                if (datos.length > 4) {
                    LocalTime hora = LocalTime.parse(datos[4]);
                    LocalDate fecha = LocalDate.parse(datos[5]);
                    String lugar = datos[6];

                    e = new Nacimiento(folio, numActa, fechaReg, registrador, hora, fecha, lugar);
                } else {
                    e = new Nacimiento(folio, numActa, fechaReg, registrador, LocalTime.NOON, fechaReg, "desconocido");
                }

                eventos.add(e);
            }
        }

        return eventos;
    }
}