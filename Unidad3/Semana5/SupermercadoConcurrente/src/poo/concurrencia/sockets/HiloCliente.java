package poo.concurrencia.sockets;

import java.net.*;
import java.io.*;
import java.util.Scanner;

// HiloCliente = Cajero
// Atiende a un cliente específico
public class HiloCliente extends Thread {

    private Socket socket;
    private int numeroCaja;

    public HiloCliente(Socket socket, int numeroCaja) {
        this.socket = socket;

        // Número de caja asignado al cliente
        this.numeroCaja = numeroCaja;
    }

    public void run() {
        try {
            // Cliente entrega su solicitud
            Scanner entrada = new Scanner(socket.getInputStream());

            // Cajero responde
            PrintStream salida = new PrintStream(socket.getOutputStream());

            // Cliente envía mensaje (pedido)
            String mensaje = entrada.nextLine();
            System.out.println("Caja #" + numeroCaja + " atiende al cliente: " + mensaje);

            salida.println("Servidor (caja #" + numeroCaja + "): solicitud recibida");

            // Fin de la atención
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}