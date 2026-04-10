package controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import patrones.factoria.CreadorEmpleado;
import javafx.scene.control.cell.PropertyValueFactory;


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

    @FXML
    public void initialize() {
        // Asociar columnas con propiedades del objeto CreadorEmpleado
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
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
        habilitarCampos();
        btnCRUD.setText("Registrar");
        btnEliminar.setVisible(false);
    }

    @FXML
    public void buscarEmpleado() {

    }

    @FXML
    public void actualizarEmpleado() {

    }

    @FXML
    public void eliminarEmpleado() {

    }

    @FXML
    public void crearUsuarios()  {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/usuario.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registro de usuarios");
            stage.show();
        }catch (Exception e) {
           System.out.println(e);
        }
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
            System.out.println("Campos Vacios");
        }
        return camposVacios;
    }
}



