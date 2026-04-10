package modelo;

import patrones.strategy.GeneradorActa;

import java.time.LocalDate;

// Clase abstracta que representa un evento general
public abstract class Evento implements Imprimible {
    //Atributos
    protected String folio;
    protected int numActa;
    protected LocalDate fechaReg;
    protected Persona registrador;
    protected GeneradorActa estrategia;


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
    // Metodo de la interfaz
    @Override
    public void imprimir() {
        System.out.println("Información basica del acta: ");
        System.out.println("Folio: " + getFolio());
        System.out.println("Número de acta: " + getNumActa());
        System.out.println("Fecha de registro: " + getFechaReg());
        // Mostrar datos de la persona que registra
        if (registrador != null) {
            System.out.println("\nRegistrador: " + registrador.getNombre() + " " + registrador.getApellido());
        }
    }

    // Permite asignar dinámicamente la estrategia de generación de actas
    public void setEstrategia(GeneradorActa estrategia) {

        this.estrategia = estrategia;
    }

    // Metodo actualizado para usar Strategy
    public void registrarEvento() {
        if (estrategia != null) {
            estrategia.generarActa(this);
        }
    }
}