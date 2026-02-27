package modelo;

public class Empleado extends PersonaSistema implements Autenticable {

    private String codigo;
    private String cargo;
    private boolean activo;
    private String usuario;
    private String contrasena;

    public Empleado(String nombre, String correo, String codigo,
                    String cargo, String usuario, String contrasena) {
        super(nombre, correo);
        this.codigo = codigo;
        this.cargo = cargo;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.activo = true;
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuario;
    }

    public boolean bloquearCuenta(Usuario usuario) {
        usuario.activo = false;
        return true;
    }

    @Override
    public boolean login(String usuario, String contrasena) {
        return this.activo &&
                this.usuario.equals(usuario) &&
                this.contrasena.equals(contrasena);
    }
}