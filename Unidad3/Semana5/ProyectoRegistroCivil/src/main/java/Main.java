import ui.EntradaDatos;
import hilos.HilosEventos;
import modelo.Evento;
import modelo.Persona;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        // Entrada de datos
        EntradaDatos entrada = new EntradaDatos(sc);

        // Leer eventos (incluye registrador)
        List<Evento> eventos = entrada.leerEventos();

        // Obtener el registrador desde el primer evento
        // (porque todos los eventos usan el mismo registrador)
        Persona registrador = eventos.get(0).getRegistrador();

        // Procesar con hilos
        HilosEventos gestor = new HilosEventos();
        gestor.registrarEventos(registrador, eventos);

        sc.close();
    }
}