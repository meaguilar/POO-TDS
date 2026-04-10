package controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Alerta;

public class PersonaControlador {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private Button btnPersona;


    @FXML
    private void initialize() {
        // Se ejecuta al cargar el FXML
    }

    @FXML
    public void setBtnCRUD() {
        String textoBoton = btnPersona.getText();

        switch (textoBoton) {
            case "Guardar":
                guardarPersona();
                break;
            case "Buscar":
                registrarEvento();
                break;
            case "Registrar-Evento":
                registrarEvento();
                break;
        }
    }

    @FXML
    private void guardarPersona() {
        try {
            int id = Integer.parseInt(txtId.getText());

            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();

            if (nombre.isEmpty() || apellido.isEmpty()) {
                Alerta.mostrarError("Todos los campos son obligatorios.");
                return;
            }

        } catch (NumberFormatException e) {
            Alerta.mostrarError("El ID debe ser un número válido.");
        }
    }

    @FXML
    private void buscarPersonaMenu (){
        btnPersona.setText("Buscar");
        txtNombre.setDisable(true);
        txtApellido.setDisable(true);
    }

    @FXML
    private void buscarPersona() {

    }

    @FXML
    public void registrarEvento() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/evento.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registro de Evento");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            Alerta.mostrarError("Error al abrir el formulario de evento.");
        }
    }


    @FXML
    private void limpiarFormulario() {
        txtId.clear();
        txtNombre.clear();
        txtApellido.clear();
    }

}