// Main.java
import modelo.Persona;
import modelo.Evento;
import modelo.Nacimiento;
import patrones.strategy.GeneradorActa;
import patrones.strategy.GeneradorActaNacimiento;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        //Construyendo objeto persona
        Persona persona = new Persona(1, "Juan", "Pérez");

        //Construyendo objeto nacimiento que hereda de evento
        Evento nacimiento = new Nacimiento(
                "F123",
                456,
                LocalDate.now(),
                persona,
                LocalTime.of(14, 30),
                LocalDate.of(2026, 02, 01),
                "San Salvador"
        );

        // Asignar estrategia concreta de generación de acta
        GeneradorActa generadorNacimiento = new GeneradorActaNacimiento();
        nacimiento.setEstrategia(generadorNacimiento);

        // Registrar el evento (invoca la estrategia)
        nacimiento.registrarEvento();

        // Agregar el evento a la lista de eventos de la persona
        persona.agregarEvento(nacimiento);

        // Mostrar información de la persona y sus eventos
        System.out.println("Cantidad de eventos registrados: " + persona.getEventos().size());


    }
}