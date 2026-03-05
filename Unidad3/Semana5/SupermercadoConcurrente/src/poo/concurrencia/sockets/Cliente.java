package poo.concurrencia.sockets;

import java.net.*;
import java.io.*;
import java.util.Scanner;

// Cliente = Persona que llega a la caja
public class Cliente {

    public static void main(String[] args) throws Exception {

      try {
          // Conexión con la caja
          Socket socket = new Socket("localhost", 5000);

          // Cliente escribe su pedido
          Scanner teclado = new Scanner(System.in);

          // Cliente recibe respuesta
          Scanner entrada = new Scanner(socket.getInputStream());

          // Cliente envía pedido
          PrintStream salida = new PrintStream(socket.getOutputStream());

          System.out.println("Escribe tu solicitud (remesas, pago de compras, pago de colectores....):");
          String mensaje = teclado.nextLine();

          // Entrega el pedido al cajero
          salida.println(mensaje);

          // Recibe confirmación
          System.out.println(entrada.nextLine());

          // Fin de la transacción
          socket.close();

      } catch (java.net.ConnectException e) {
          System.out.println("No se pudo conectar al servidor. Probablemente ya esta apagado.");
      }
    }
}