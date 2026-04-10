package patrones.strategy;

import modelo.Evento;

public class GeneradorActaNacimiento implements GeneradorActa {

    @Override
    public void generarActa(Evento evento) {
        // Un solo string
        String acta = "\n"
                + "Generando acta de nacimiento...\n"
                + "\nInformación basica del acta:\n"
                + "Folio: " + evento.getFolio() + "\n"
                + "Número de acta: " + evento.getNumActa() + "\n"
                + "Fecha de registro: " + evento.getFechaReg() + "\n";

        if (evento instanceof modelo.Nacimiento n) {
            acta += "Hora de nacimiento: " + n.getHora() + "\n"
                    + "Fecha de nacimiento: " + n.getFecha() + "\n"
                    + "Lugar de nacimiento: " + n.getLugar() + "\n";
        }

        System.out.println(acta);
    }
}