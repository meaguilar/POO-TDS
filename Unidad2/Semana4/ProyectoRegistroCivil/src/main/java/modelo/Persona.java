package modelo;
// Importando librerias
import java.time.LocalDate;
import java.time.Period;

// Librerias necesarias para la colección Lista
import java.util.ArrayList;
import java.util.List;

public class Persona{

    // Atributos
    private int id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNac;
    private int edad;
    private List<Evento> eventos; // Lista simple agregada


    // Constructor por defecto
    public Persona() {
    }

    // Constructor parametrizado
    public Persona(int id, String nombre, String apellido, LocalDate fechaNac) {
        // Uso de this
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        //Agregaremos el metodo de actualizar edad para dicho atributo
        actualizarEdad();
        // Lista
        this.eventos = new ArrayList<>();


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

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Setter y getter de eventos
    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    // Otros métodos
    public void actualizarEdad() {
        if (fechaNac != null) {
            this.edad = Period.between(fechaNac, LocalDate.now()).getYears();
        }
    }

    public Persona obtenerDatos() {
        return this;
    }

    // Agregar evento a lista
    public void agregarEvento(Evento evento) {
        this.eventos.add(evento);
    }

}
