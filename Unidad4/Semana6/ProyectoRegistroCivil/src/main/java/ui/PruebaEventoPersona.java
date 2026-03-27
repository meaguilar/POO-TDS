package ui;

import modelo.Nacimiento;
import modelo.Persona;
import patrones.strategy.GeneradorActaNacimiento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PruebaEventoPersona {
    // Devuelve lista de personas con sus eventos ya agregados
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