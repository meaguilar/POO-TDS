package modelo;
// Importando librerias
import java.time.LocalDate;
import java.time.LocalTime;

// Clase Nacimiento hereda de Evento
public class Nacimiento extends Evento {
    //Atributos
    private int id;
    private LocalTime hora;
    private String lugar;
    private Persona persona;

    //Constructor parametrizado
    public Nacimiento(int id, String folio, int numActa, LocalDate fechaReg,
                      LocalTime hora, String lugar, Persona persona) {
        // Uso de super
        super(folio, numActa, fechaReg);
        this.id = id;
        this.hora = hora;
        this.lugar = lugar;
        this.persona = persona;
    }

    // Aplicando polimorfismo
    @Override
    public void registrarEvento() {
        System.out.println("=== Registro de Nacimiento ===");
        System.out.println("Folio: " + folio);
        System.out.println("Número de Acta: " + numActa);
        System.out.println("Fecha de Registro: " + fechaReg);
        System.out.println("Persona: " + persona.getNombre() + " " + persona.getApellido());
        System.out.println("Lugar: " + lugar);
        System.out.println("Hora: " + hora);
    }

    public void generarActa() {
        System.out.println("Acta de nacimiento generada.");
    }
}
