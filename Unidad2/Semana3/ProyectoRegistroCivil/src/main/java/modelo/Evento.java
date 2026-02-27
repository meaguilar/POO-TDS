package modelo;
//Importando librerias
import java.time.LocalDate;

// Clase abstracta
public abstract class Evento implements Imprimible {
    //Atributos
    protected String folio;
    protected int numActa;
    protected LocalDate fechaReg;


    //Constructor parametrizado
    public Evento(String folio, int numActa, LocalDate fechaReg) {
        this.folio = folio;
        this.numActa = numActa;
        this.fechaReg = fechaReg;
    }

    // Getters y Setters
    public String getFolio() {
        return folio;
    }

    public int getNumActa() {
        return numActa;
    }

    public LocalDate getFechaReg() {
        return fechaReg;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setNumActa(int numActa) {
        this.numActa = numActa;
    }

    public void setFechaReg(LocalDate fechaReg) {
        this.fechaReg = fechaReg;
    }

    //Otros métodos
    public void registrarEvento() {
        System.out.println("Evento registrado con folio: " + folio);
    }

    // Metodo abstracto: sin implementación
    public abstract void generarActa();

    // Metodo de la interfaz
    @Override
    public void imprimir() {
        System.out.println("Imprimiendo información básica del evento...");
        System.out.println("Folio: " + folio);
        System.out.println("Número de acta: " + numActa);
        System.out.println("Fecha: " + fechaReg);
    }

}
