package modelo;

public class Persona {

    // Atributos
    private int id;
    private String nombre;
    private String apellido;

    // Constructor por defecto
    public Persona() {
    }

    // Constructor parametrizado
    public Persona(int id, String nombre, String apellido) {
        // Uso de this
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Métodos setter y getter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    // Otros métodos
    public Persona obtenerDatos() {
        return this;
    }

}
