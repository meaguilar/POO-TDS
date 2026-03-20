package modelo;
// Importando librerias
import java.time.LocalDate;
import java.time.LocalTime;

// Clase Nacimiento hereda de Evento
public class Nacimiento extends Evento {
    //Atributos
    private LocalTime hora;
    private LocalDate fecha;
    private String lugar;

    //Constructor parametrizado
    public Nacimiento(String folio, int numActa, LocalDate fechaReg, Persona registrador,
                      LocalTime hora, LocalDate fecha, String lugar) {
        // Uso de super
        super(folio, numActa, fechaReg, registrador);
        this.hora = hora;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getLugar() {
        return lugar;
    }

    // Aplicando polimorfismo
    // Metodo que implementa de interfaz
    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Hora de nacimiento: " + getHora());
        System.out.println("Fecha de nacimiento: " + getFecha());
        System.out.println("Lugar de nacimiento: " + getLugar());
    }
}
