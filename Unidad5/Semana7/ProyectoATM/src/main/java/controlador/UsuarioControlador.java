package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.UsuarioATM;

public class UsuarioControlador {

    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private TextField txtPin;
    @FXML private TextField txtSaldo;

    @FXML private TableColumn<UsuarioATM, String> colNombre;
    @FXML private TableColumn<UsuarioATM, String> colCorreo;
    @FXML private TableColumn<UsuarioATM, String> colUsuario;
    @FXML private TableColumn<UsuarioATM, String> colContrasena;
    @FXML private TableColumn<UsuarioATM, String> colPin;
    @FXML private TableColumn<UsuarioATM, String> colNumeroCuenta;
    @FXML private TableColumn<UsuarioATM, Double> colSaldo;
    @FXML private TableColumn<UsuarioATM, String> colCodEmpleado;


    public void initialize() {
        // Vincular columnas con atributos del modelo
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colContrasena.setCellValueFactory(new PropertyValueFactory<>("contrasena"));
        colPin.setCellValueFactory(new PropertyValueFactory<>("pin"));
        colNumeroCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroTarjeta"));
        colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        colCodEmpleado.setCellValueFactory(new PropertyValueFactory<>("codigoEmpleado"));

    }

    @FXML
    public void registrarUsuario() {

    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtCorreo.clear();
        txtUsuario.clear();
        txtContrasena.clear();
        txtPin.clear();
        txtSaldo.clear();
    }
}


