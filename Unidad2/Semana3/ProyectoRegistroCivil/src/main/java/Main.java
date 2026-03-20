// Importar las clases
import modelo.Persona;
import modelo.Evento;
import modelo.Nacimiento;

// Importar librerias
import java.time.LocalDate;
import java.time.LocalTime;

// Se refleja el cambio
public class Main {
    public static void main(String[] args) {

        //Construyendo objeto persona
        Persona persona = new Persona(1, "Juan", "Pérez");

        //Construyendo objeto nacimiento que hereda de evento
        Evento nacimiento = new Nacimiento(
                "F123",
                456,
                LocalDate.now(),
                persona,
                LocalTime.of(14, 30),
                LocalDate.of(2026, 02, 01),
                "San Salvador"
        );

        //Invocando metodo que heredo nacimiento el cual implementa polimorfismo
        nacimiento.registrarEvento();
        // Invocando metodo Imprimir
        nacimiento.imprimir();

    }
}
