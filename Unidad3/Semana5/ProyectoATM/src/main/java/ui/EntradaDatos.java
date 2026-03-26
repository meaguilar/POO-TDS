package ui;

import modelo.Usuario;
import patrones.factoria.CreadorEmpleado;
import patrones.factoria.CreadorUsuarioATM;

import java.util.Random;
import java.util.Scanner;

public class EntradaDatos {

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    // Solicita los datos del empleado y retorna un CreadorEmpleado
    public CreadorEmpleado solicitarDatosEmpleado() {
        System.out.println("=== Registro de Empleado ===");

        System.out.print("Nombre del empleado: ");
        String nombre = scanner.nextLine();

        System.out.print("Email del empleado: ");
        String correo = scanner.nextLine();

        System.out.print("Código del empleado: ");
        String codigo = scanner.nextLine();

        System.out.print("Cargo del empleado: ");
        String cargo = scanner.nextLine();

        System.out.print("Usuario del empleado: ");
        String usuario = scanner.nextLine();

        System.out.print("Contraseña del empleado: ");
        String contrasena = scanner.nextLine();

        // Crear y retornar la factoría para crear usuarios
        return new CreadorUsuarioATM(nombre, correo, codigo, cargo, usuario, contrasena);
    }

    // Solicita los datos del usuario y crea un UsuarioATM usando la factoría
    public Usuario solicitarCrearUsuario(CreadorEmpleado creadorEmpleado) {
        System.out.println("=== Registro de Usuario ATM ===");

        System.out.print("Nombre del usuario: ");
        String nombre = scanner.nextLine();

        System.out.print("Email del usuario: ");
        String correo = scanner.nextLine();

        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        System.out.print("PIN: ");
        String pin = scanner.nextLine();

        // Generar número de cuenta aleatorio de 10 dígitos
        String numCuenta = generarNumeroCuenta();

        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(scanner.nextLine());

        // Crear el usuario usando la factoría
        return creadorEmpleado.crearUsuario(nombre, correo, usuario, contrasena, pin, numCuenta, saldo);
    }

    public static String generarNumeroCuenta() {
        Random random = new Random();
        StringBuilder numeroCuenta = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int digito = random.nextInt(10); // 0 a 9
            numeroCuenta.append(digito);
        }
        return numeroCuenta.toString();
    }

}