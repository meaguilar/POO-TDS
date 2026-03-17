package modelo;

public class CreadorUsuarioATM extends CreadorEmpleado {

    public CreadorUsuarioATM(String nombre, String correo,
                             String codigo, String cargo,
                             String usuario, String contrasena) {
        super(nombre, correo, codigo, cargo, usuario, contrasena);
    }

    @Override
    public Usuario crearUsuario(String nombre,
                                String correo,
                                String usuario,
                                String contrasena,
                                String pin,
                                String numeroTarjeta,
                                double saldo) {

        return new UsuarioATM(
                nombre,
                correo,
                usuario,
                contrasena,
                pin,
                numeroTarjeta,
                saldo
        );
    }
}
