package dao;

import patrones.factoria.CreadorEmpleado;
import patrones.factoria.CreadorUsuarioATM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOImpl implements EmpleadoDAO {

    private Connection conexion;

    public EmpleadoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardar(CreadorEmpleado e) {
        String sql = "INSERT INTO Empleado(nombre, correo, codigo, cargo, activo, usuario, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getCorreo());
            ps.setString(3, e.getCodigo());
            ps.setString(4, e.getCargo());
            ps.setBoolean(5, e.isActivo());
            ps.setString(6, e.getUsuario());
            ps.setString(7, e.getContrasena());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actualizar(CreadorEmpleado e) {
        String sql = "UPDATE Empleado SET nombre=?, correo=?, cargo=?, activo=?, usuario=?, contrasena=? WHERE codigo=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getCorreo());
            ps.setString(3, e.getCargo());
            ps.setBoolean(4, e.isActivo());
            ps.setString(5, e.getUsuario());
            ps.setString(6, e.getContrasena());
            ps.setString(7, e.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(String codigo) {
        String sql = "DELETE FROM Empleado WHERE codigo=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public CreadorEmpleado buscarPorCodigo(String codigo){
        CreadorEmpleado empleado = null;
        String sql = "SELECT * FROM Empleado WHERE codigo=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    empleado = new CreadorUsuarioATM(
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("codigo"),
                            rs.getString("cargo"),
                            rs.getString("usuario"),
                            rs.getString("contrasena")
                    );
                    empleado.setActivo(rs.getBoolean("activo"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return empleado;
    }

    @Override
    public List<CreadorEmpleado> listarTodos() {
        List<CreadorEmpleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM Empleado";
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                // Crear la instancia concreta CreadorUsuarioATM
                CreadorEmpleado e = new CreadorUsuarioATM(
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("codigo"),
                        rs.getString("cargo"),
                        rs.getString("usuario"),
                        rs.getString("contrasena")
                );

                e.setActivo(rs.getBoolean("activo"));
                lista.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

}