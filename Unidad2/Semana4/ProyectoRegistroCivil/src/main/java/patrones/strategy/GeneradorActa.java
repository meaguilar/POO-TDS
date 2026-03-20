package patrones.strategy;

import modelo.Evento;

// Interfaz para generar actas mediante el patrón Strategy.
public interface GeneradorActa {
    void generarActa(Evento evento);
}
