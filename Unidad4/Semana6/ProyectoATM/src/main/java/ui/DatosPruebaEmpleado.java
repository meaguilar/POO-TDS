package ui;

import dao.EmpleadoDAO;
import dao.EmpleadoDAOImpl;
import patrones.factoria.CreadorEmpleado;
import patrones.factoria.CreadorUsuarioATM;
import db.Conexion;

import java.sql.Connection;
import java.util.List;

public class DatosPruebaEmpleado {

    public void pruebaEmpleadoEjecutar() {
        // Obtener instancia de conexión singleton
        Conexion conexionSingleton = Conexion.getInstancia();
        conexionSingleton.conectar();
        Connection conexion = conexionSingleton.getConexionBD();

        if (conexion == null) {
            System.err.println("No se pudo establecer la conexión a la base de datos.");
            return;
        }

        try {
            // Crear DAO con la conexión
            EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl(conexion);

            // Crear un empleado de prueba
            CreadorUsuarioATM empleado = new CreadorUsuarioATM(
                    "Juan Pérez",
                    "perez@banco.com",
                    "EMP2341",
                    "Jefe",
                    "juanp",
                    "123"
            );
            // Guardar empleado
            empleadoDAO.guardar(empleado);
            System.out.println("Empleado registrado: " + empleado.getNombre());

            // Listar todos los empleados
            listarEmpleados(empleadoDAO);

            // Actualizar el empleado
             empleado.setCargo("Director");
             empleado.setContrasena("nuevopass");
             empleadoDAO.actualizar(empleado);
             System.out.println("Empleado actualizado: " + empleado.getNombre());

            // Listar todos los empleados
            listarEmpleados(empleadoDAO);

            // Buscar un empleado por código
            buscarEmpleadoPorCodigo(empleadoDAO, "EMP233");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Cerrar conexión
            conexionSingleton.desconectar();
        }
    }

    // Método para listar empleados
    private void listarEmpleados(EmpleadoDAO empleadoDAO) throws Exception {
        List<CreadorEmpleado> listaEmpleados = empleadoDAO.listarTodos();
        System.out.println("===== Lista de Empleados =====");
        for (CreadorEmpleado e : listaEmpleados) {
            System.out.println("Nombre: " + e.getNombre());
            System.out.println("Correo: " + e.getCorreo());
            System.out.println("Código: " + e.getCodigo());
            System.out.println("Cargo: " + e.getCargo());
            System.out.println("Usuario: " + e.getUsuario());
            System.out.println("Activo: " + e.isActivo());
            System.out.println("---------------------------");
        }
    }

    // Método para buscar empleado por código
    private void buscarEmpleadoPorCodigo(EmpleadoDAO empleadoDAO, String codigo) throws Exception {
        CreadorEmpleado empleadoEncontrado = empleadoDAO.buscarPorCodigo(codigo);

        System.out.println("===== Resultado de buscar por código: " + codigo + " =====");
        if (empleadoEncontrado != null) {
            System.out.println("Nombre: " + empleadoEncontrado.getNombre());
            System.out.println("Correo: " + empleadoEncontrado.getCorreo());
            System.out.println("Código: " + empleadoEncontrado.getCodigo());
            System.out.println("Cargo: " + empleadoEncontrado.getCargo());
            System.out.println("Usuario: " + empleadoEncontrado.getUsuario());
            System.out.println("Activo: " + empleadoEncontrado.isActivo());
        } else {
            System.out.println("No se encontró un empleado con el código: " + codigo);
        }
    }
}