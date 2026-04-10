package patrones.factoria;

import modelo.Autenticable;
import modelo.PersonaSistema;
import modelo.Usuario;

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
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
        usuario.setActivo(activo);
        return true;
    }

    @Override
    public boolean login(String usuario, String contrasena) {
        return this.activo &&
                this.usuario.equals(usuario) &&
                this.contrasena.equals(contrasena);
    }
}