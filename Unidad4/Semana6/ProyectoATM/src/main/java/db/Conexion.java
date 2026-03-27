package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Instancia única (Singleton)
    private static Conexion instancia;

    // Objeto JDBC Connection
    private Connection conexionBD;

    // Parámetros de conexión para SQL Server
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BD_BANCO;trustServerCertificate=true;";
    private static final String USER = "sa";
    // Es necesario contar con un usuario válido configurado en SQL Server
    private static final String PASS = "TuPassword123!";

    // Constructor privado para evitar instanciación externa
    private Conexion() {
        try {
            // Registro explícito del driver (opcional en JDBC 4+)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver de SQL Server no encontrado.");
        }
    }

    // Metodo sincronizado para obtener la instancia única
    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    // Establece conexión si no está activa
    public void conectar() {
        try {
            if (conexionBD == null || conexionBD.isClosed()) {
                conexionBD = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Conexión a la base de datos establecida.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // Cierra la conexión si está abierta
    public void desconectar() {
        try {
            if (conexionBD != null && !conexionBD.isClosed()) {
                conexionBD.close();
                System.out.println("Conexión a la base de datos cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    // Getter para acceder a la conexión activa
    public Connection getConexionBD() {
        return conexionBD;
    }

    // Metodo de prueba
    public static void testConexion() {
        Conexion conexion = Conexion.getInstancia();
        conexion.conectar();

        Connection conn = conexion.getConexionBD();
        if (conn != null) {
            try {
                System.out.println("Conexión activa: " + !conn.isClosed());
            } catch (SQLException e) {
                System.err.println("Error al verificar la conexión: " + e.getMessage());
            }
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }

        conexion.desconectar();
    }
}