package ui;

import dao.UsuarioDAOImpl;
import modelo.Usuario;
import modelo.UsuarioATM;
import db.Conexion;

import java.sql.Connection;
import java.util.List;

public class DatosPruebaUsuario {

    public void pruebaUsuarioATM() {
        // Conexión a la BD
        Conexion conexionSingleton = Conexion.getInstancia();
        conexionSingleton.conectar();
        Connection conexion = conexionSingleton.getConexionBD();

        if (conexion == null) {
            System.err.println("No se pudo establecer la conexión a la base de datos.");
            return;
        }

        try {
            // DAO de prueba
            UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(conexion);

            // Supongamos que el empleado que registra ya tiene id_empleado = 1
            String codigoEmpleadoExistente = "EMP2341";

            // Crear un usuario ATM directamente
            UsuarioATM usuario = new UsuarioATM(
                    "Mario López",
                    "mario@banco.com",
                    "mrae",
                    "pass123",
                    "0001",
                    "1234567822",
                    1000.0,
                    codigoEmpleadoExistente
            );

            // Guardar en la BD
            usuarioDAO.guardar(usuario);
            System.out.println("Usuario ATM registrado: " + usuario.getNombre());

            // Listar los usuarios ATM registrados
            listarUsuarios(usuarioDAO);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conexionSingleton.desconectar();
        }
    }

    // Método separado para listar usuarios
    private void listarUsuarios(UsuarioDAOImpl usuarioDAO){
        List<Usuario> listaUsuarios = usuarioDAO.listarUsuarios();
        System.out.println("===== Lista de Usuarios =====");
        for (Usuario u : listaUsuarios) {
            System.out.println("Nombre: " + u.getNombre());
            System.out.println("Correo: " + u.getCorreo());
            System.out.println("Usuario login: " + u.getUsuario());
            System.out.println("Contraseña: " + u.getContrasena());

            // Si es UsuarioATM, mostramos información específica
            if (u instanceof UsuarioATM) {
                UsuarioATM atm = (UsuarioATM) u;
                System.out.println("PIN: " + atm.getPin());
                System.out.println("Número de tarjeta: " + atm.getNumeroTarjeta());
                System.out.println("Saldo: " + atm.getSaldo());
                System.out.println("Empleado que registró: " + atm.getCodigoEmpleado());
            }

            System.out.println("---------------------------");
        }
    }
}