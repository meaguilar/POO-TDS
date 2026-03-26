import hilos.HilosUsuarios;
import modelo.Usuario;
import patrones.factoria.CreadorEmpleado;
import ui.EntradaDatos;


public class Main {

    public static void main(String[] args) {

        System.out.println("=== Iniciando aplicación ATM ===");

        // Crear entrada de datos
        EntradaDatos entrada = new EntradaDatos();

        // Solicitar datos del empleado y obtener la factoría
        CreadorEmpleado creadorEmpleado = entrada.solicitarDatosEmpleado();

        // Solicitar datos de los usuarios y crearlos
        // Esto se hace en el main para evitar Scanner en hilos
        Usuario usuario1 = entrada.solicitarCrearUsuario(creadorEmpleado);
        Usuario usuario2 = entrada.solicitarCrearUsuario(creadorEmpleado);

        // Pasar los usuarios a la clase de hilos para procesarlos
        HilosUsuarios.ejecutar(usuario1,usuario2);

        System.out.println("=== Aplicación finalizada ===");

    }
}

