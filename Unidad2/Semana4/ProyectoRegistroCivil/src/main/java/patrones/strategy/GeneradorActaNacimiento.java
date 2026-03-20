package patrones.strategy;

import modelo.Evento;
import modelo.Nacimiento;

public class GeneradorActaNacimiento implements GeneradorActa {

    @Override
    public void generarActa(Evento evento) {
        System.out.println("\n....Generando acta de nacimiento...\n");
        evento.imprimir();
        if (evento instanceof Nacimiento) {
            Nacimiento nacimiento = (Nacimiento) evento;
            System.out.println("Hora de nacimiento: " + nacimiento.getHora());
            System.out.println("Fecha de nacimiento: " + nacimiento.getFecha());
            System.out.println("Lugar de nacimiento: " + nacimiento.getLugar());
        } else {
            System.out.println("Error: El evento no es un nacimiento válido");
        }
    }
}