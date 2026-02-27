package modelo;

import java.time.LocalDate;

// Clase abstracta que representa un evento general
public abstract class Evento implements Imprimible {
    protected String folio;
    protected int numActa;
    protected LocalDate fechaReg;

    // Estrategia para la generación del acta (patrón Strategy)
    private GeneradorActa estrategia;

    // Constructor
    public Evento(String folio, int numActa, LocalDate fechaReg) {
        this.folio = folio;
        this.numActa = numActa;
        this.fechaReg = fechaReg;
    }

    // Getter y setter
    public String getFolio() { return folio; }
    public int getNumActa() { return numActa; }
    public LocalDate getFechaReg() { return fechaReg; }
    public void setFolio(String folio) { this.folio = folio; }
    public void setNumActa(int numActa) { this.numActa = numActa; }
    public void setFechaReg(LocalDate fechaReg) { this.fechaReg = fechaReg; }

    // Implementación del metodo definido en la interfaz
    @Override
    public void imprimir() {
        System.out.println("Imprimiendo información general del evento...");
        System.out.println("Folio: " + folio);
        System.out.println("Número de acta: " + numActa);
        System.out.println("Fecha: " + fechaReg);
    }

    // Permite asignar dinámicamente la estrategia de generación de actas
    public void setEstrategia(GeneradorActa estrategia) {
        this.estrategia = estrategia;
    }

    // Metodo actualizado para usar Strategy
    public void registrarEvento() {
        System.out.println("Evento registrado con folio: " + folio);
        if (estrategia != null) {
            estrategia.generarActa(this);
        }
    }
}