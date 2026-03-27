package dao;

import modelo.Evento;
import java.util.List;

public interface EventoDAO {
    void guardar(Evento evento);
    List<Evento> listarPorPersona(int idPersona);
}