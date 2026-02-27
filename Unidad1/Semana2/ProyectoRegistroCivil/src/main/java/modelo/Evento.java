package modelo;
//Importando librerias
import java.time.LocalDate;

// Clase padre
public class Evento {
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

}
