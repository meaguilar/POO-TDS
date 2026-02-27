// Main.java
import modelo.Persona;
import modelo.Evento;
import modelo.Nacimiento;
import modelo.GeneradorActa;
import modelo.GeneradorActaNacimiento;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        // ️Crear persona
        Persona persona = new Persona(1, "Juan", "Pérez", LocalDate.of(2000, 5, 10));


        // Crear evento de nacimiento
        Evento nacimiento = new Nacimiento(
                0001,
                "F123",
                456,
                LocalDate.now(),
                LocalTime.of(14, 30),
                "Hospital General",
                persona
        );

        // Asignar estrategia concreta de generación de acta
        GeneradorActa generadorNacimiento = new GeneradorActaNacimiento();
        nacimiento.setEstrategia(generadorNacimiento);

        // Registrar el evento (invoca la estrategia)
        nacimiento.registrarEvento();

        // Agregar el evento a la lista de eventos de la persona
        persona.agregarEvento(nacimiento);

        nacimiento.imprimir();

        // Mostrar información de la persona y sus eventos
        System.out.println("Persona: " + persona.getNombre() + " " + persona.getApellido());
        System.out.println("Edad: " + persona.getEdad());
        System.out.println("Cantidad de eventos registrados: " + persona.getEventos().size());


    }
}