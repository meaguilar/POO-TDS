package controlador;

import dao.EmpleadoDAO;
import dao.EmpleadoDAOImpl;
import db.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import patrones.factoria.CreadorEmpleado;
import patrones.factoria.CreadorUsuarioATM;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.Optional;

import util.Alerta;

public class EmpleadoControlador {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtCargo;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Button btnCRUD;
    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<CreadorEmpleado> tablaEmpleados;
    @FXML
    private TableColumn<CreadorEmpleado, String> colCodigo;
    @FXML
    private TableColumn<CreadorEmpleado, String> colNombre;
    @FXML
    private TableColumn<CreadorEmpleado, String> colCorreo;
    @FXML
    private TableColumn<CreadorEmpleado, String> colCargo;
    @FXML
    private TableColumn<CreadorEmpleado, String> colUsuario;
    @FXML
    private TableColumn<CreadorEmpleado, Boolean> colActivo;

    private ObservableList<CreadorEmpleado> listaEmpleados = FXCollections.observableArrayList();
    private EmpleadoDAO empleadoDAO;
    private CreadorEmpleado empleadoActual;

    public EmpleadoControlador() {
        Conexion conexionSingleton = Conexion.getInstancia();
        conexionSingleton.conectar();
        Connection conexion = conexionSingleton.getConexionBD();

        empleadoDAO = new EmpleadoDAOImpl(conexion);
    }

    @FXML
    public void initialize() {

        // Asociar columnas con propiedades del objeto CreadorEmpleado
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));

        // Cargar los empleados desde la BD
        cargarEmpleados();
    }

    private void cargarEmpleados() {
        listaEmpleados.clear();
        listaEmpleados.addAll(empleadoDAO.listarTodos());
        tablaEmpleados.setItems(listaEmpleados);
        tablaEmpleados.refresh();
    }
    @FXML
    public void registrarEmpleado() {
        // Validar campos vacíos

        if (!validarCamposVacios()) {
            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String codigo = txtCodigo.getText();
            String cargo = txtCargo.getText();
            String usuario = txtUsuario.getText();
            String contrasena = txtContrasena.getText();
            empleadoActual = new CreadorUsuarioATM(
                    nombre, correo, codigo, cargo, usuario, contrasena
            );

            empleadoDAO.guardar(empleadoActual);

            // AGREGAR ALERTA
            Alerta.mostrarAlerta(Alert.AlertType.INFORMATION, "Empleado registrado exitosamente");

            // Actualizar tabla automáticamente
            cargarEmpleados();
            limpiarFormulario();
        }

    }

    @FXML
    public void setBtnCRUD() {
        String textoBoton = btnCRUD.getText();

        switch (textoBoton) {
            case "Registrar":
                registrarEmpleado();
                break;
            case "Buscar":
                buscarEmpleado();
                break;
            case "Actualizar":
                actualizarEmpleado();
                break;
        }
    }

    @FXML
    public void buscarCodigoEmpleadoMenu() {
        // Preparar formulario para búsqueda
        deshabilitarCampos();
        btnEliminar.setVisible(true);
        btnCRUD.setText("Buscar");
    }

    @FXML
    public void registrarEmpleadoMenu(){
        limpiarFormulario();
        habilitarCampos();
        btnCRUD.setText("Registrar");
        btnEliminar.setVisible(false);
    }

    @FXML
    public void buscarEmpleado() {

        if (btnCRUD.getText().equals("Buscar")) {
            String codigo = txtCodigo.getText();
            if (codigo.isEmpty()) {
                Alerta.mostrarAlerta(Alert.AlertType.WARNING, "Ingresa un código de empleado");
                return;
            }

            CreadorEmpleado empleado = empleadoDAO.buscarPorCodigo(codigo);
            if (empleado != null) {
                // Llenar los campos
                txtNombre.setText(empleado.getNombre());
                txtCorreo.setText(empleado.getCorreo());
                txtCargo.setText(empleado.getCargo());
                txtUsuario.setText(empleado.getUsuario());
                txtContrasena.setText(empleado.getContrasena());

                // Habilitar campos para edicion
                habilitarCampos();

                // Guardar empleado actual para actualizar
                empleadoActual = empleado;

                btnCRUD.setText("Actualizar");

            } else {
                Alerta.mostrarAlerta(Alert.AlertType.ERROR, "No se encontro el empleado");
            }
        }
    }

    @FXML
    public void actualizarEmpleado() {
        if (btnCRUD.getText().equals("Actualizar")) {

            // Actualizar datos en la instancia y BD
            empleadoActual.setNombre(txtNombre.getText());
            empleadoActual.setCorreo(txtCorreo.getText());
            empleadoActual.setCargo(txtCargo.getText());
            empleadoActual.setUsuario(txtUsuario.getText());
            empleadoActual.setContrasena(txtContrasena.getText());

            empleadoDAO.actualizar(empleadoActual);

            cargarEmpleados();

            limpiarFormulario();
            btnCRUD.setText("Registrar");
            btnEliminar.setVisible(false);
        }
    }

    @FXML
    public void eliminarEmpleado() {
        String codigo = txtCodigo.getText();
        if (codigo.isEmpty()) {
            Alerta.mostrarAlerta(Alert.AlertType.WARNING, "Ingresar un código de empleado");
            return;
        }else{
            empleadoDAO.eliminar(codigo);
            limpiarFormulario();
            cargarEmpleados();
        }
    }
    @FXML
    public void crearUsuarios()  {
        try {
            if (empleadoActual == null) {
                Alerta.mostrarAlerta(Alert.AlertType.WARNING, "Se debe ingresar un empleado ante de registrar usuario");
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/usuario.fxml"));
            Parent root = loader.load();

            UsuarioControlador controller = loader.getController();
            controller.setCreadorEmpleado(empleadoActual);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registro de usuarios");
            stage.show();
        }catch (Exception e) {
           Alerta.mostrarAlerta(Alert.AlertType.WARNING, "Error al crear el usuario");
        }
    }

    @FXML
    public void registrarUsuarioCodEmpleado(){

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar empleado");
        dialog.setHeaderText("Ingrese el código del empleado");
        dialog.setContentText("Código:");

        Optional<String> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {
            String codigo = resultado.get();

            // Buscar empleado en la BD
            empleadoActual = empleadoDAO.buscarPorCodigo(codigo);

            if (empleadoActual != null) {
                Alerta.mostrarAlerta(Alert.AlertType.INFORMATION,
                        "Empleado encontrado: " + empleadoActual.getNombre());

                // Abrir ventana de registro de usuario
                crearUsuarios();
            } else {
                Alerta.mostrarAlerta(Alert.AlertType.ERROR,
                        "No se encontró un empleado con ese código.");
            }
        }
    }

    public CreadorEmpleado getEmpleadoActual() {
        return empleadoActual;
    }

    // resetear el formulario
    private void limpiarFormulario() {
        txtCodigo.clear();
        txtNombre.clear();
        txtCorreo.clear();
        txtCargo.clear();
        txtUsuario.clear();
        txtContrasena.clear();

        habilitarCampos();

        btnCRUD.setText("Registrar");
        empleadoActual = null;
    }
    // habilitar campos
    public void habilitarCampos() {
        txtNombre.setDisable(false);
        txtCorreo.setDisable(false);
        txtCargo.setDisable(false);
        txtUsuario.setDisable(false);
        txtContrasena.setDisable(false);
    }
    // deshabilitar campos
    public void deshabilitarCampos(){
        txtNombre.setDisable(true);
        txtCorreo.setDisable(true);
        txtCargo.setDisable(true);
        txtUsuario.setDisable(true);
        txtContrasena.setDisable(true);
    }
    // validar campos vacios
    public boolean validarCamposVacios() {
        boolean camposVacios = false;

        // Limpiar estilos previos
        txtNombre.setStyle("");
        txtCorreo.setStyle("");
        txtCodigo.setStyle("");
        txtCargo.setStyle("");
        txtUsuario.setStyle("");
        txtContrasena.setStyle("");

        // Validar cada campo y cambiar borde si está vacío
        if (txtNombre.getText().isEmpty()) {
            txtNombre.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            camposVacios = true;
        }
        if (txtCorreo.getText().isEmpty()) {
            txtCorreo.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            camposVacios = true;
        }
        if (txtCodigo.getText().isEmpty()) {
            txtCodigo.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            camposVacios = true;
        }
        if (txtCargo.getText().isEmpty()) {
            txtCargo.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            camposVacios = true;
        }
        if (txtUsuario.getText().isEmpty()) {
            txtUsuario.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            camposVacios = true;
        }
        if (txtContrasena.getText().isEmpty()) {
            txtContrasena.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            camposVacios = true;
        }

        if (camposVacios) {
            Alerta.mostrarAlerta(Alert.AlertType.WARNING, "Por favor, completa todos los campos marcados en rojo.");
        }
        return camposVacios;
    }
}



