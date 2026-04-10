package org.example.proyecto.ui;

import org.example.proyecto.modelo.*;
import org.example.proyecto.patrones.strategy.GeneradorActaNacimiento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase encargada de la entrada de datos por consola.
 * <p>
 * Permite registrar personas y eventos de nacimiento,
 * validando la información ingresada por el usuario.
 * </p>
 */
public class EntradaDatos {

    /** Scanner utilizado para la entrada de datos */
    private Scanner sc;

    /** Formato utilizado para validar la hora */
    private DateTimeFormatter timeFormatter;

    /**
     * Constructor de la clase EntradaDatos.
     *
     * @param sc objeto Scanner utilizado para la lectura de datos
     */
    public EntradaDatos(Scanner sc) {
        this.sc = sc;
        this.timeFormatter = DateTimeFormatter.ofPattern("H:mm");
    }

    /**
     * Lee los datos de una persona registradora desde consola.
     *
     * @return objeto Persona con la información ingresada
     */
    public Persona leerPersona() {
        System.out.println(" ... Datos de la persona que registra....");

        int id = leerEntero("ID: ");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        return new Persona(id, nombre, apellido);
    }

    /**
     * Lee una lista de eventos asociados a una persona.
     *
     * @param registrador persona que registra los eventos
     * @return lista de eventos creados
     */
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

            Nacimiento evento = new Nacimiento(
                    folio,
                    numActa,
                    fechaReg,
                    registrador,
                    hora,
                    fechaNacimiento,
                    lugar
            );

            evento.setEstrategia(new GeneradorActaNacimiento());

            eventos.add(evento);
        }

        return eventos;
    }

    // ================= VALIDACIONES =================

    /**
     * Lee un número entero validado desde consola.
     *
     * @param mensaje mensaje que se muestra al usuario
     * @return número entero ingresado
     */
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

    /**
     * Lee una fecha validada en formato YYYY-MM-DD.
     *
     * @param mensaje mensaje que se muestra al usuario
     * @return objeto LocalDate válido
     */
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

    /**
     * Lee una hora validada en formato HH:MM.
     *
     * @param mensaje mensaje que se muestra al usuario
     * @return objeto LocalTime válido
     */
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