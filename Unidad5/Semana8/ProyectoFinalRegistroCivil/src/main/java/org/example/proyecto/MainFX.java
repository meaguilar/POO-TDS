package org.example.proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación JavaFX.
 * <p>
 * Esta clase es el punto de entrada del sistema Registro Civil.
 * Se encarga de inicializar la interfaz gráfica utilizando FXML.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class MainFX extends Application {

    /**
     * Método principal de inicio de la aplicación JavaFX.
     * <p>
     * Carga la vista definida en el archivo {@code persona.fxml},
     * la asigna a una escena y la muestra en la ventana principal (Stage).
     * </p>
     *
     * @param stage ventana principal proporcionada por JavaFX
     * @throws Exception si ocurre un error al cargar el archivo FXML
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/persona.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setTitle("Proyecto Registro Civil");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método principal de ejecución.
     * <p>
     * Inicia la aplicación JavaFX.
     * </p>
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
}