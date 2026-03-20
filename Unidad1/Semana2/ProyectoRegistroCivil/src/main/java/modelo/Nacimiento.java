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
                      LocalTime hora, LocalDate fechaNacimiento, String lugar) {
        // Uso de super
        super(folio, numActa, fechaReg, registrador);
        this.hora = hora;
        this.lugar = lugar;
    }

    // Aplicando polimorfismo
    @Override
    public void registrarEvento() {
        super.registrarEvento();
        System.out.println("=== Registro de Nacimiento ===");
        System.out.println("Folio: " + folio);
        System.out.println("Número de Acta: " + numActa);
        System.out.println("Fecha de Registro: " + fechaReg);
        System.out.println("Lugar: " + lugar);
        System.out.println("Hora: " + hora);
    }
    
}
