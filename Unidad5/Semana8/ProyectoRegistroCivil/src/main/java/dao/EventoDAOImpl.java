package dao;

import modelo.Evento;
import modelo.Nacimiento;
import modelo.Persona;
import persistencia.ArchivoTexto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventoDAOImpl implements EventoDAO {

    private ArchivoTexto archivo;

    public EventoDAOImpl() {
        this.archivo = new ArchivoTexto("data/eventos.txt");
    }

    @Override
    public void guardar(Evento evento) {
        // Guardamos folio, numActa, id registrador, fecha de registro
        String linea = evento.getFolio() + "," +
                evento.getNumActa() + "," +
                evento.getRegistrador().getId() + "," +
                evento.getFechaReg();
        // Si es Nacimiento, agregamos hora, fecha y lugar
        if (evento instanceof Nacimiento n) {
            linea += "," + n.getHora() + "," + n.getFecha() + "," + n.getLugar();
        }
        archivo.escribir(linea);
    }

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
                    // Es Nacimiento
                    LocalTime hora = LocalTime.parse(datos[4]);
                    LocalDate fecha = LocalDate.parse(datos[5]);
                    String lugar = datos[6];
                    e = new Nacimiento(folio, numActa, fechaReg, registrador, hora, fecha, lugar);
                } else {
                    // Evento genérico
                    e = new Nacimiento(folio, numActa, fechaReg, registrador, LocalTime.NOON, fechaReg, "desconocido");
                }

                eventos.add(e);
            }
        }
        return eventos;
    }
}