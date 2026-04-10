package hilos;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import modelo.Evento;

import java.util.List;

public class HilosEventosFX {

    public void registrarEventoFX(List<Evento> eventos, TextArea area, Runnable onFinish) {

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                for (Evento evento : eventos) {
                    try {
                        evento.registrarEvento();

                        String resultado = "Folio: " + evento.getFolio()
                                + " | Acta: " + evento.getNumActa()
                                + " | Fecha: " + evento.getFechaReg();

                        Platform.runLater(() -> area.appendText(resultado + "\n"));

                    } catch (Exception e) {
                        Platform.runLater(() -> area.appendText("Error: " + e.getMessage() + "\n"));
                    }
                }

                // Ejecutar callback al final
                if (onFinish != null) {
                    Platform.runLater(onFinish);
                }

                return null;
            }
        };

        new Thread(task).start();
    }
}

