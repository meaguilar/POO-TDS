import dao.EmpleadoDAO;
import dao.EmpleadoDAOImpl;
import dao.UsuarioDAOImpl;
import hilos.HilosUsuarios;
import patrones.factoria.CreadorEmpleado;
import ui.EntradaDatos;
import db.Conexion;
import modelo.UsuarioATM;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        // Obtener conexión
        Conexion conexionSingleton = Conexion.getInstancia();
        conexionSingleton.conectar();
        Connection conexion = conexionSingleton.getConexionBD();

        if (conexion == null) {
            System.err.println("No se pudo establecer la conexión a la base de datos.");
            return;
        }

        try {
            // Crear DAOs
            EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl(conexion);
            UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(conexion);

            // Crear EntradaDatos
            EntradaDatos entrada = new EntradaDatos();

            // Registrar empleado
            CreadorEmpleado empleado = entrada.registrarEmpleado(empleadoDAO);

            // Tomar el código del empleado
            String codigoEmpleado = empleado.getCodigo();

            // Registrar dos usuarios ATM
            UsuarioATM u1 = entrada.registrarUsuario(empleado, usuarioDAO, codigoEmpleado);
            UsuarioATM u2 = entrada.registrarUsuario(empleado, usuarioDAO, codigoEmpleado);

            // 3Ejecutar hilos para procesar usuarios
            HilosUsuarios.ejecutar(u1, u2);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Cerrar la conexión
            conexionSingleton.desconectar();
        }
    }
}