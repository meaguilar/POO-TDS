package modelo;

public class Usuario {

    protected String nombre;
    protected String correo;

    public Usuario(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public boolean login(String usuarioIngresado, String correoIngresado) {
        // Simulación simple de login
        return this.nombre.equals(usuarioIngresado) &&
                this.correo.equals(correoIngresado);
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }
}