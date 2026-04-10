package controlador;

import javafx.concurrent.Task;
import dao.UsuarioDAOImpl;
import db.Conexion;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Usuario;
import modelo.UsuarioATM;
import patrones.factoria.CreadorEmpleado;
import util.Alerta;

import java.sql.Connection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class UsuarioControlador {

    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private TextField txtPin;
    @FXML private TextField txtSaldo;
    @FXML private ProgressIndicator progressIndicator;

    @FXML private TableView<UsuarioATM> tablaUsuarios;

    @FXML private TableColumn<UsuarioATM, String> colNombre;
    @FXML private TableColumn<UsuarioATM, String> colCorreo;
    @FXML private TableColumn<UsuarioATM, String> colUsuario;
    @FXML private TableColumn<UsuarioATM, String> colContrasena;
    @FXML private TableColumn<UsuarioATM, String> colPin;
    @FXML private TableColumn<UsuarioATM, String> colNumeroCuenta;
    @FXML private TableColumn<UsuarioATM, Double> colSaldo;
    @FXML private TableColumn<UsuarioATM, String> colCodEmpleado;

    private ObservableList<UsuarioATM> listaUsuarios = FXCollections.observableArrayList();
    private UsuarioDAOImpl usuarioDAO;
    private CreadorEmpleado creadorEmpleado;

    public UsuarioControlador() {
        Conexion conexionSingleton = Conexion.getInstancia();
        conexionSingleton.conectar();
        Connection conexion = conexionSingleton.getConexionBD();

        usuarioDAO = new UsuarioDAOImpl(conexion);
    }

    // Recibir el empleado desde otra pantalla
    public void setCreadorEmpleado(CreadorEmpleado creadorEmpleado) {
        this.creadorEmpleado = creadorEmpleado;
    }

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

        // Cargar datos desde la BD
        cargarUsuarios();
    }

    @FXML
    public void registrarUsuario() {
        // Bloquear UI y mostrar progress
        progressIndicator.setVisible(true);
        setUIEnabled(false);

        Task<Void> tarea = new Task<>() {
            @Override
            protected Void call() {
                try {
                    String numCuenta = generarNumeroCuentaUnico();

                    if (creadorEmpleado == null) {
                        Platform.runLater(() ->
                                Alerta.mostrarAlerta(Alert.AlertType.WARNING, "No hay empleado seleccionado")
                        );
                        return null;
                    }

                    UsuarioATM nuevoUsuario = (UsuarioATM) creadorEmpleado.crearUsuario(
                            txtNombre.getText(),
                            txtCorreo.getText(),
                            txtUsuario.getText(),
                            txtContrasena.getText(),
                            txtPin.getText(),
                            numCuenta,
                            Double.parseDouble(txtSaldo.getText())
                    );

                    nuevoUsuario.setCodigoEmpleado(creadorEmpleado.getCodigo());

                    // Guardar en BD
                    usuarioDAO.guardar(nuevoUsuario);

                    Platform.runLater(() -> {
                        Alerta.mostrarAlerta(Alert.AlertType.CONFIRMATION, "Usuario registrado correctamente");
                        cargarUsuarios();
                        limpiarFormulario();
                    });

                } catch (Exception e) {
                    Platform.runLater(() ->
                            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error: " + e.getMessage())
                    );
                }
                return null;
            }
        };

        tarea.setOnSucceeded(e -> {
            progressIndicator.setVisible(false);
            setUIEnabled(true);
        });
        tarea.setOnFailed(e -> {
            progressIndicator.setVisible(false);
            setUIEnabled(true);
        });
        tarea.setOnCancelled(e -> {
            progressIndicator.setVisible(false);
            setUIEnabled(true);
        });

        new Thread(tarea).start();
    }

    private void setUIEnabled(boolean enabled) {
        txtNombre.setDisable(!enabled);
        txtCorreo.setDisable(!enabled);
        txtUsuario.setDisable(!enabled);
        txtContrasena.setDisable(!enabled);
        txtPin.setDisable(!enabled);
        txtSaldo.setDisable(!enabled);
    }

    private void cargarUsuarios() {
        listaUsuarios.clear();
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();

        // Convertir List<Usuario> a List<UsuarioATM> filtrando/casteando
        for (Usuario u : usuarios) {
            if (u instanceof UsuarioATM) {
                listaUsuarios.add((UsuarioATM) u);
            }
        }

        tablaUsuarios.setItems(listaUsuarios);
    }

    private static Set<String> cuentasGeneradas = new HashSet<>();

    public static String generarNumeroCuentaUnico() {
        Random random = new Random();
        String numero;

        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(random.nextInt(10));
            }
            numero = sb.toString();
        } while (cuentasGeneradas.contains(numero));

        cuentasGeneradas.add(numero);
        return numero;
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


