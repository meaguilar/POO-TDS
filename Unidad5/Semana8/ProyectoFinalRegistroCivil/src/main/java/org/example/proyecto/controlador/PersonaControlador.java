package org.example.proyecto.controlador;

import org.example.proyecto.dao.PersonaDAO;
import org.example.proyecto.dao.PersonaDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyecto.modelo.Persona;
import org.example.proyecto.util.Alerta;

/**
 * Controlador encargado de la gestión de personas en el sistema Registro Civil.
 * <p>
 * Permite registrar, buscar y gestionar personas, además de abrir el
 * formulario de eventos asociado a una persona seleccionada.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class PersonaControlador {

    /** Campo de texto para el ID de la persona */
    @FXML private TextField txtId;

    /** Campo de texto para el nombre de la persona */
    @FXML private TextField txtNombre;

    /** Campo de texto para el apellido de la persona */
    @FXML private TextField txtApellido;

    /** Botón principal que cambia de funcionalidad según el estado */
    @FXML private Button btnPersona;

    /** Acceso a la capa de persistencia de personas */
    private PersonaDAO personaDAO;

    /** Objeto persona actualmente seleccionado o registrado */
    private Persona persona;

    /**
     * Constructor del controlador.
     * Inicializa la implementación del DAO.
     */
    public PersonaControlador() {
        personaDAO = new PersonaDAOImpl();
    }

    /**
     * Método de inicialización automática del controlador JavaFX.
     */
    @FXML
    private void initialize() {
        // Se ejecuta al cargar el FXML
    }

    /**
     * Ejecuta la acción del botón según su estado actual.
     * Puede guardar, buscar o abrir el registro de evento.
     */
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

    /**
     * Guarda una nueva persona en el sistema.
     * <p>
     * Valida los datos ingresados y los almacena en archivo.
     * </p>
     */
    @FXML
    private void guardarPersona() {

        txtId.setDisable(false);
        txtNombre.setDisable(false);
        txtApellido.setDisable(false);
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();

            if (nombre.isEmpty() || apellido.isEmpty()) {
                Alerta.mostrarError("Todos los campos son obligatorios.");
                return;
            }

            persona = new Persona(id, nombre, apellido);

            personaDAO.guardar(persona);

            Alerta.mostrarInfo("Persona registrada correctamente (data/personas.txt)");
            limpiarFormulario();

        } catch (NumberFormatException e) {
            Alerta.mostrarError("El ID debe ser un número válido.");
        }
    }

    /**
     * Cambia el estado del formulario para habilitar la búsqueda de persona.
     */
    @FXML
    private void buscarPersonaMenu() {
        btnPersona.setText("Buscar");
        txtNombre.setDisable(true);
        txtApellido.setDisable(true);
    }

    /**
     * Busca una persona por su ID en el sistema.
     * Si la encuentra, muestra sus datos en pantalla.
     */
    @FXML
    private void buscarPersona() {
        try {
            int id = Integer.parseInt(txtId.getText());

            Persona p = personaDAO.obtenerPorId(id);

            if (p != null) {
                Alerta.mostrarInfo("Persona encontrada." + p.getNombre() + " " + p.getApellido());
                txtId.setDisable(false);
                txtId.clear();
                persona = p;
                registrarEvento();
            } else {
                Alerta.mostrarError("Persona no encontrada.");
            }

        } catch (NumberFormatException e) {
            Alerta.mostrarError("Ingrese un ID válido.");
        }
    }

    /**
     * Abre la ventana de registro de eventos asociada a la persona seleccionada.
     */
    @FXML
    public void registrarEvento() {
        try {
            if (persona == null) {
                Alerta.mostrarError("Primero debe registrar o buscar una persona.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/evento.fxml"));
            Parent root = loader.load();

            EventoControlador controller = loader.getController();
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

    /**
     * Limpia los campos del formulario de persona.
     */
    @FXML
    private void limpiarFormulario() {
        txtId.clear();
        txtNombre.clear();
        txtApellido.clear();
    }

    /**
     * Retorna la persona actualmente seleccionada.
     *
     * @return objeto Persona activo
     */
    public Persona getPersona() {
        return persona;
    }
}