package org.example.proyecto.util;

import javafx.scene.control.Alert;

/**
 * Clase utilitaria para mostrar mensajes emergentes en JavaFX.
 * <p>
 * Proporciona métodos estáticos para mostrar alertas de error
 * e información al usuario de forma sencilla y reutilizable.
 * </p>
 */
public class Alerta {

    /**
     * Muestra una alerta de error con un mensaje específico.
     *
     * @param msg mensaje que se mostrará en la alerta
     */
    public static void mostrarError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta informativa con un mensaje específico.
     *
     * @param msg mensaje que se mostrará en la alerta
     */
    public static void mostrarInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}