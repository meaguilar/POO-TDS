package dao;

import modelo.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void guardar(Usuario usuario);
    //void actualizar(Usuario usuario);
    //void eliminar(String usuarioLogin);
    //Usuario buscarPorUsuario(String usuarioLogin);
    List<Usuario> listarUsuarios();
}
