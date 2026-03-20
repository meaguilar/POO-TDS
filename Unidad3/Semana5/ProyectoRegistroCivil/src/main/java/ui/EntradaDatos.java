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

    // Datos del registrador
    public Persona leerPersona() {
        System.out.println(" ... Datos de la persona que registra....");

        int id = leerEntero("ID: ");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        return new Persona(id, nombre, apellido);
    }

    // Eventos
    public List<Evento> leerEventos() {

        Persona registrador = leerPersona();

        int cantidad = leerEntero("\n¿Cuántos eventos desea registrar?: ");
        List<Evento> eventos = new ArrayList<>();

        for (int i = 1; i <= cantidad; i++) {

            System.out.println("\n=== EVENTO " + i + " ===");

            System.out.print("Folio: ");
            String folio = sc.nextLine();

            int numActa = leerEntero("Número de acta: ");

            LocalDate fechaReg = LocalDate.now();

            LocalTime hora = leerHora("Hora de nacimiento (HH:MM): ");

            LocalDate fechaNacimiento = leerFecha("Fecha de nacimiento: ");

            System.out.print("Lugar de nacimiento: ");
            String lugar = sc.nextLine();

            // CONSTRUCTOR CORRECTO
            Nacimiento evento = new Nacimiento(
                    folio,
                    numActa,
                    fechaReg,
                    registrador,
                    hora,
                    fechaNacimiento,
                    lugar
            );

            // Strategy
            evento.setEstrategia(new GeneradorActaNacimiento());

            eventos.add(evento);
        }

        return eventos;
    }

    // ================= VALIDACIONES =================

    private int leerEntero(String mensaje) {
        int valor;

        while (true) {
            try {
                System.out.print(mensaje);
                valor = Integer.parseInt(sc.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
    }

    private LocalDate leerFecha(String mensaje) {
        LocalDate fecha;

        while (true) {
            try {
                System.out.print(mensaje);
                fecha = LocalDate.parse(sc.nextLine());
                return fecha;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato inválido. Use YYYY-MM-DD.");
            }
        }
    }

    private LocalTime leerHora(String mensaje) {
        LocalTime hora;

        while (true) {
            try {
                System.out.print(mensaje);
                hora = LocalTime.parse(sc.nextLine(), timeFormatter);
                return hora;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato inválido. Use HH:MM.");
            }
        }
    }
}