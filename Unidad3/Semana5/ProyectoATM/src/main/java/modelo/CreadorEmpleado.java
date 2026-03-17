package modelo;

// Cambia a clase abstracta
public  abstract class CreadorEmpleado extends PersonaSistema implements Autenticable {

    private String codigo;
    private String cargo;
    private boolean activo;
    private String usuario;
    private String contrasena;

    public CreadorEmpleado(String nombre, String correo, String codigo,
                           String cargo, String usuario, String contrasena) {
        super(nombre, correo);
        this.codigo = codigo;
        this.cargo = cargo;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.activo = true;
    }


    // FACTORY METHOD
    public abstract Usuario crearUsuario(
            String nombre,
            String correo,
            String usuario,
            String contrasena,
            String pin,
            String numeroTarjeta,
            double saldo
    );

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