package org.example.proyecto.hilos;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import org.example.proyecto.modelo.Evento;

import java.util.List;

/**
 * Clase encargada del procesamiento de eventos en segundo plano en JavaFX.
 * <p>
 * Utiliza {@link Task} y {@link Platform#runLater} para ejecutar operaciones
 * sin bloquear la interfaz gráfica, actualizando el UI de forma segura.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class HilosEventosFX {

    /**
     * Ejecuta el registro de eventos en un hilo de fondo y actualiza la interfaz gráfica.
     * <p>
     * Cada evento es procesado de forma asíncrona, mostrando los resultados en un
     * {@link TextArea}. Al finalizar el proceso se ejecuta un callback opcional.
     * </p>
     *
     * @param eventos lista de eventos a procesar
     * @param area componente de interfaz donde se mostrarán los resultados
     * @param onFinish acción opcional a ejecutar al finalizar el procesamiento
     */
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

                if (onFinish != null) {
                    Platform.runLater(onFinish);
                }

                return null;
            }
        };

        new Thread(task).start();
    }
}