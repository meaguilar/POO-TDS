package controlador;

import dao.PersonaDAO;
import dao.PersonaDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Persona;
import util.Alerta;

public class PersonaControlador {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private Button btnPersona;

    private PersonaDAO personaDAO;
    private Persona persona;

    public PersonaControlador() {
        personaDAO = new PersonaDAOImpl();
    }

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
                buscarPersona();
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

            persona = new Persona(id, nombre, apellido);

            // Guardar en archivo txt
            personaDAO.guardar(persona);

            Alerta.mostrarInfo("Persona registrada correctamente (data/personas.txt)");
            limpiarFormulario();

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
        try {
            int id = Integer.parseInt(txtId.getText());

            Persona p = personaDAO.obtenerPorId(id);

            if (p != null) {
                txtNombre.setText(p.getNombre());
                txtApellido.setText(p.getApellido());
                Alerta.mostrarInfo("Persona encontrada.");
                txtId.setDisable(false);
                persona = p;
                btnPersona.setText("Registrar-Evento");
            } else {
                Alerta.mostrarError("Persona no encontrada.");
            }

        } catch (NumberFormatException e) {
            Alerta.mostrarError("Ingrese un ID válido.");
        }
    }

    @FXML
    public void registrarEvento() {
        try {
            // Validar que haya una persona registrada
            if (persona == null) {
                Alerta.mostrarError("Primero debe registrar o buscar una persona.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/evento.fxml"));
            Parent root = loader.load();

            // Obtener el controller correcto
            EventoControlador controller = loader.getController();

            // Pasar la persona al evento
            controller.setRegistrador(persona);

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

    public Persona getPersona() {
        return persona;
    }


}