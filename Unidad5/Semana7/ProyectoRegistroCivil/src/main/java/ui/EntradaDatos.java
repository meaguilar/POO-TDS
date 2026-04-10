package ui;

import modelo.*;
import patrones.strategy.GeneradorActaNacimiento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntradaDatos {

    private Scanner sc;
    private DateTimeFormatter timeFormatter;

    public EntradaDatos(Scanner sc) {
        this.sc = sc;
        this.timeFormatter = DateTimeFormatter.ofPattern("H:mm");
    }

    // Leer datos de la persona registradora
    public Persona leerPersona() {
        System.out.println(" ... Datos de la persona que registra....");

        int id = leerEntero("ID: ");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        return new Persona(id, nombre, apellido);
    }

    // Leer eventos asociados a una persona
    public List<Evento> leerEventos(Persona registrador) {
        int cantidad = leerEntero("\n¿Cuántos eventos desea registrar?: ");
        List<Evento> eventos = new ArrayList<>();

        for (int i = 1; i <= cantidad; i++) {

            System.out.println("\n=== EVENTO " + i + " ===");

            System.out.print("Folio: ");
            String folio = sc.nextLine();

            int numActa = leerEntero("Número de acta: ");

            LocalDate fechaReg = LocalDate.now();

            LocalTime hora = leerHora("Hora de nacimiento (HH:MM): ");

            LocalDate fechaNacimiento = leerFecha("Fecha de nacimiento (YYYY-MM-DD): ");

            System.out.print("Lugar de nacimiento: ");
            String lugar = sc.nextLine();

            // Crear el evento
            Nacimiento evento = new Nacimiento(
                    folio,
                    numActa,
                    fechaReg,
                    registrador,
                    hora,
                    fechaNacimiento,
                    lugar
            );

            // Asignar estrategia de acta
            evento.setEstrategia(new GeneradorActaNacimiento());

            eventos.add(evento);
        }

        return eventos;
    }

    // ================= VALIDACIONES =================

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
    }

    private LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return LocalDate.parse(sc.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato inválido. Use YYYY-MM-DD.");
            }
        }
    }

    private LocalTime leerHora(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return LocalTime.parse(sc.nextLine(), timeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato inválido. Use HH:MM.");
            }
        }
    }
}