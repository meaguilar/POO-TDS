package hilos;

import modelo.Usuario;
import modelo.UsuarioATM;

public class HilosUsuarios {

    public static void ejecutar(Usuario u1, Usuario u2) {

        // Hilo 1
        Thread hilo1 = new Thread(() -> {
            System.out.println("Hilo 1: procesando usuario...");
            if (u1.login(u1.getUsuario(), u1.getContrasena())) {
                ((UsuarioATM) u1).consultarSaldo();
            }
            System.out.println("Hilo 1 terminado");
        });

        // Hilo 2
        Thread hilo2 = new Thread(() -> {
            System.out.println("Hilo 2: procesando usuario...");
            if (u2.login(u2.getUsuario(), u2.getContrasena())) {
                ((UsuarioATM) u2).consultarSaldo();
            }
            System.out.println("Hilo 2 terminado");
        });

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();

        // Esperar a que los hilos terminen
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
