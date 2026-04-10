package hilos;


import javafx.application.Platform;
import javafx.concurrent.Task;
import modelo.Usuario;
import modelo.UsuarioATM;

public class HilosUsuariosFX {

    public static void procesarUsuarios(Usuario u1, Usuario u2, Runnable onFinish) {
        Task<Void> tarea = new Task<>() {
            @Override
            protected Void call() {
                procesar(u1, "Hilo 1");
                procesar(u2, "Hilo 2");
                return null;
            }

            private void procesar(Usuario u, String nombreHilo) {
                System.out.println(nombreHilo + ": procesando usuario...");
                try {
                    if (u.login(u.getUsuario(), u.getContrasena()) && u instanceof UsuarioATM) {
                        ((UsuarioATM) u).consultarSaldo();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(nombreHilo + " terminado");
            }
        };

        tarea.setOnSucceeded(e -> Platform.runLater(onFinish));

        new Thread(tarea).start();
    }
}
