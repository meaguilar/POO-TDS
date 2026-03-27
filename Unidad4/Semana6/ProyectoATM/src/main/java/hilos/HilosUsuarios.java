package hilos;

import modelo.Usuario;
import modelo.UsuarioATM;

public class HilosUsuarios {

    /**
     * Ejecuta dos usuarios en hilos para procesar operaciones, sin tocar la BD
     */
    public static void ejecutar(Usuario u1, Usuario u2) {

        // Hilo 1
        Thread hilo1 = new Thread(() -> {
            System.out.println("Hilo 1: procesando usuario...");
            try {
                // Solo procesar, no guardar
                if (u1.login(u1.getUsuario(), u1.getContrasena())) {
                    ((UsuarioATM) u1).consultarSaldo();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Hilo 1 terminado");
        });

        // Hilo 2
        Thread hilo2 = new Thread(() -> {
            System.out.println("Hilo 2: procesando usuario...");
            try {
                // Solo procesar, no guardar
                if (u2.login(u2.getUsuario(), u2.getContrasena())) {
                    ((UsuarioATM) u2).consultarSaldo();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Hilo 2 terminado");
        });

        // Iniciar hilos
        hilo1.start();
        hilo2.start();

        // Esperar a que terminen
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}