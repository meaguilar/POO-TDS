package dao;

import modelo.Usuario;
import modelo.UsuarioATM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    private Connection conexion;

    public UsuarioDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardar(Usuario usuario) {

        // Solo guardamos si es UsuarioATM
        if (!(usuario instanceof UsuarioATM)) {
            System.out.println("Tipo de usuario no soportado para esta implementación.");
            return;
        }
        UsuarioATM atm = (UsuarioATM) usuario;

        String sql = "INSERT INTO UsuarioATM(nombre, correo, usuario_login, contrasena, pin, numero_tarjeta, saldo, codigo_empleado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, atm.getNombre());
            ps.setString(2, atm.getCorreo());
            ps.setString(3, atm.getUsuario());
            ps.setString(4, atm.getContrasena());
            ps.setString(5, atm.getPin());
            ps.setString(6, atm.getNumeroTarjeta());
            ps.setDouble(7, atm.getSaldo());
            // asumiendo que UsuarioATM tiene idEmpleado o se asigna previamente
            ps.setString(8, atm.getCodigoEmpleado());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // Nuevo método para listar todos los usuarios ATM
    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM UsuarioATM";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UsuarioATM usuario = new UsuarioATM(
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("usuario_login"),
                        rs.getString("contrasena"),
                        rs.getString("pin"),
                        rs.getString("numero_tarjeta"),
                        rs.getDouble("saldo"),
                        rs.getString("codigo_empleado") // id del empleado que registró
                );
                lista.add(usuario); // Se agrega como Usuario
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }
}
