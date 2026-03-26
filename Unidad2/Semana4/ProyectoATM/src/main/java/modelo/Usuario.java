package modelo;

public abstract class Usuario extends PersonaSistema implements Autenticable {

    protected String usuario;
    protected String contrasena;
    protected boolean activo;

    public Usuario(String nombre, String correo, String usuario, String contrasena) {
        super(nombre, correo);
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.activo = true;
    }

    public String getUsuario() {
        return usuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public void cambiarContrasena(String nuevaContrasena) {
        this.contrasena = nuevaContrasena;
    }

    public void actualizarDatos(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }
    @Override
    public boolean login(String usuario, String contrasena) {
        return this.activo &&
                this.usuario.equals(usuario) &&
                this.contrasena.equals(contrasena);
    }
}