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
        Persona persona = new Persona(1, "Juan", "Pérez", LocalDate.of(2000, 5, 10));


        //Construyendo objeto nacimiento que hereda de evento
        Evento nacimiento = new Nacimiento( 101,
                "F123",
                456,
                LocalDate.now(),
                LocalTime.of(14, 30),
                "Hospital General",
                persona
        );


        //Invocando metodo que heredo nacimiento el cual implementa polimorfismo
        nacimiento.registrarEvento();

    }
}
