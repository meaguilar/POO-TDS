package modelo;

public class GeneradorActaNacimiento implements GeneradorActa {

    @Override
    public void generarActa(Evento evento) {
        System.out.println("Generando acta de nacimiento...");
        System.out.println("Folio: " + evento.getFolio());
        System.out.println("Número de Acta: " + evento.getNumActa());
        System.out.println("Fecha de Registro: " + evento.getFechaReg());
    }
}