package poo.concurrencia.sockets;

import java.net.*;

// Servidor = Supermercado
// Cada cliente será atendido por un cajero (hilo)
public class Servidor {

    // Contador de cajas (número de caja)
    private static int contadorCajas = 1;

    // Apagar después de 3 clientes
    private static final int MAX_CLIENTES = 3;

    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("Servidor iniciado... esperando clientes...");

        int clientesAtendidos = 0;

        while (clientesAtendidos < MAX_CLIENTES) {

            // Cliente llega a la caja (socket)
            Socket cliente = servidor.accept();

            int numeroCaja = contadorCajas++;
            System.out.println("Cliente conectado a la caja #" + numeroCaja);

            // Cajero atiende al cliente
            new HiloCliente(cliente, numeroCaja).start();
            clientesAtendidos++;
        }

        System.out.println("Servidor apagado, despues de atender " + MAX_CLIENTES + " clientes.");
        System.out.println("No se aceptaran mas conexiones.");
        servidor.close();
    }
}