package hilos;
import modelo.*;

public class HilosUsuarios {

    public static void ejecutar() {

        CreadorEmpleado creadorEmpleado = new CreadorUsuarioATM(
                "Ana Lopez",
                "ana@mail.com",
                "EMP001",
                "Gerente",
                "anaAdmin",
                "admin123"
        );

        // Hilo 1
        Thread hilo1 = new Thread(() -> {
            System.out.println("Hilo 1: creando usuario...");

            Usuario u1 = creadorEmpleado.crearUsuario(
                    "Juan Perez",
                    "juan@mail.com",
                    "juan123",
                    "1234",
                    "9999",
                    "1234567890",
                    1000.0
            );

            if (u1.login("juan123", "1234")) {
                ((UsuarioATM) u1).consultarSaldo();
            }

            System.out.println("Hilo 1 terminado");
        });

        // Hilo 2
        Thread hilo2 = new Thread(() -> {
            System.out.println("Hilo 2: creando usuario...");

            Usuario u2 = creadorEmpleado.crearUsuario(
                    "Maria Lopez",
                    "maria@mail.com",
                    "maria123",
                    "5678",
                    "8888",
                    "0987654321",
                    2000.0
            );

            if (u2.login("maria123", "5678")) {
                ((UsuarioATM) u2).consultarSaldo();
            }

            System.out.println("Hilo 2 terminado");
        });

        // Iniciar hilos
        hilo1.start();
        hilo2.start();

        // Uso de excepciones
        try {
            // join() hace que el programa principal espere
            // hasta que el hilo1 termine su ejecución
            hilo1.join();

            // join() hace que el programa principal espere
            // hasta que el hilo2 termine su ejecución
            hilo2.join();

        } catch (InterruptedException e) {
            // Si el hilo es interrumpido mientras espera,
            // se captura la excepción aquí
            e.printStackTrace();
        }
    }
}