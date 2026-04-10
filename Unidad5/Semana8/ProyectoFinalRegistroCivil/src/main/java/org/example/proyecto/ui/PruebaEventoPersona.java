package org.example.proyecto.ui;

import org.example.proyecto.modelo.Nacimiento;
import org.example.proyecto.modelo.Persona;
import org.example.proyecto.patrones.strategy.GeneradorActaNacimiento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de prueba para la creación de objetos Persona con eventos asociados.
 * <p>
 * Se utiliza para simular datos del sistema Registro Civil sin necesidad
 * de entrada por consola o interfaz gráfica.
 * </p>
 */
public class PruebaEventoPersona {

    /**
     * Crea una lista de personas con eventos de nacimiento ya asociados.
     * <p>
     * Este método se utiliza principalmente para pruebas del sistema,
     * inicializando datos de ejemplo con el patrón Strategy aplicado.
     * </p>
     *
     * @return lista de personas con eventos registrados
     */
    public List<Persona> crearPersonasConEventos() {
        List<Persona> personas = new ArrayList<>();

        // Persona 1
        Persona persona1 = new Persona(1, "Juan", "Pérez");

        Nacimiento nacimiento1 = new Nacimiento(
                "F001",
                101,
                LocalDate.now(),
                persona1,
                LocalTime.of(10, 30),
                LocalDate.of(2023, 5, 15),
                "Hospital Central"
        );

        nacimiento1.setEstrategia(new GeneradorActaNacimiento());
        persona1.agregarEvento(nacimiento1);

        // Persona 2
        Persona persona2 = new Persona(2, "Ana", "Gómez");

        Nacimiento nacimiento2 = new Nacimiento(
                "F002",
                102,
                LocalDate.now(),
                persona2,
                LocalTime.of(14, 45),
                LocalDate.of(2023, 6, 20),
                "Clínica Norte"
        );

        nacimiento2.setEstrategia(new GeneradorActaNacimiento());
        persona2.agregarEvento(nacimiento2);

        personas.add(persona1);
        personas.add(persona2);

        return personas;
    }
}