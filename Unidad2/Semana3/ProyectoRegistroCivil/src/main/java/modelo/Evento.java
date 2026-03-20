package modelo;
//Importando librerias
import java.time.LocalDate;

// Clase abstracta
public abstract class Evento implements Imprimible {

    //Atributos
    protected String folio;
    protected int numActa;
    protected LocalDate fechaReg;
    protected Persona registrador;


    //Constructor parametrizado
    public Evento(String folio, int numActa, LocalDate fechaReg, Persona registrador) {
        this.folio = folio;
        this.numActa = numActa;
        this.fechaReg = fechaReg;
        this.registrador = registrador;
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
    public void setRegistrador(Persona registrador) {
        this.registrador = registrador;
    }
    public Persona getRegistrador() {
        return registrador;
    }

    //Otros métodos
    public void registrarEvento() {
        System.out.println("Evento registrado por: " + this.getRegistrador().getNombre() + " " + this.getRegistrador().getApellido());
    }
    // Metodo de la interfaz
    @Override
    public void imprimir() {
        System.out.println("Imprimiendo información básica del evento...");
        System.out.println("Folio: " + getFolio());
        System.out.println("Número de acta: " + getNumActa());
        System.out.println("Fecha de registro: " + getFechaReg());
    }

}
