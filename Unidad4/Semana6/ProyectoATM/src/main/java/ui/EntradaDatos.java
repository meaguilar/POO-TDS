package ui;

import dao.EmpleadoDAO;
import dao.UsuarioDAOImpl;
import modelo.UsuarioATM;
import patrones.factoria.CreadorEmpleado;
import patrones.factoria.CreadorUsuarioATM;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class EntradaDatos {

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    /**
     * Solicita los datos del empleado, crea la factoría y lo registra en la BD
     * @param empleadoDAO DAO de empleados
     * @return CreadorEmpleado listo para crear usuarios
     */
    public CreadorEmpleado registrarEmpleado(EmpleadoDAO empleadoDAO) {
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

        // Crear la factoría concreta
        CreadorEmpleado creadorEmpleado = new CreadorUsuarioATM(nombre, correo, codigo, cargo, usuario, contrasena);

        // Guardar el empleado en la BD
        empleadoDAO.guardar(creadorEmpleado);

        System.out.println("Empleado registrado: " + nombre);
        return creadorEmpleado;
    }

    /**
     * Solicita los datos del usuario, lo crea usando la factoría y lo guarda en la BD
     * @param creadorEmpleado factoría que registra al usuario
     * @param usuarioDAO DAO de usuarios
     * @param codigoEmpleado String del empleado que registra
     * @return UsuarioATM creado y guardado
     */
    public UsuarioATM registrarUsuario(CreadorEmpleado creadorEmpleado, UsuarioDAOImpl usuarioDAO, String codigoEmpleado) {
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

        // Numero de cuenta
        String numCuenta = generarNumeroCuentaUnico();
        System.out.println("Numero de cuenta asignado: " + numCuenta);

        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(scanner.nextLine());

        // Crear usuario usando la factoría
        UsuarioATM nuevoUsuario = (UsuarioATM) creadorEmpleado.crearUsuario(nombre, correo, usuario, contrasena, pin, numCuenta, saldo);

        // Asignar el código del empleado
        nuevoUsuario.setCodigoEmpleado(codigoEmpleado);

        // Guardar en la BD
        usuarioDAO.guardar(nuevoUsuario);

        System.out.println("Usuario ATM registrado: " + nuevoUsuario.getNombre());
        return nuevoUsuario;
    }

    private static Set<String> cuentasGeneradas = new HashSet<>();

    public static String generarNumeroCuentaUnico() {
        Random random = new Random();
        String numero;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(random.nextInt(10));
            }
            numero = sb.toString();
        } while (cuentasGeneradas.contains(numero));
        cuentasGeneradas.add(numero);
        return numero;
    }
}