package modelo;

public abstract class PersonaSistema {
    protected String nombre;
    protected String correo;

    public PersonaSistema(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

}
