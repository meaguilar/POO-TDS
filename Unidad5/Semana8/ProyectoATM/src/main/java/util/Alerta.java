package util;
import javafx.scene.control.Alert;

public class Alerta {

    public static void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje);
        alerta.showAndWait();
    }

}
