package dao;

import modelo.Persona;

public interface PersonaDAO {
    void guardar(Persona persona);
    Persona obtenerPorId(int id);
}