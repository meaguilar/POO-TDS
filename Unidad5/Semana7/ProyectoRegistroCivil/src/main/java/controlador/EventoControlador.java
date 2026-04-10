package controlador;

import catalogo.Departamento;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.Alerta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventoControlador {

    @FXML private TextField txtFolio;
    @FXML private TextField txtNumActa;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private TextArea txtResultado;
    @FXML private Button btnGuardarEvento;
    @FXML private Spinner<Integer> spHora;
    @FXML private Spinner<Integer> spMinuto;
    @FXML private ComboBox<Departamento> cbDepartamento;
    @FXML private ProgressIndicator progressIndicator;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

    @FXML
    private void initialize() {
        // Hora 0-23
        spHora.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12)
        );

        // Minutos 0-59
        spMinuto.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0)
        );
        cbDepartamento.getItems().addAll(Departamento.values());

        // Bloquear fechas futuras
        dpFechaNacimiento.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
    }

    @FXML
    private void guardarEvento() {

        String folio = txtFolio.getText();
        String numActaStr = txtNumActa.getText();
        LocalDate fechaRegistro = LocalDate.now();

        int h = spHora.getValue();
        int m = spMinuto.getValue();
        LocalTime hora = LocalTime.of(h, m);

        LocalDate fechaNacimiento = dpFechaNacimiento.getValue();
        Departamento dep = cbDepartamento.getValue();

        // Validar departamento
        if (dep == null) {
            Alerta.mostrarError("Seleccione un departamento.");
            return;
        }

        String lugar = dep.name();

        // Validar campos vacíos básicos
        if (folio == null || folio.isEmpty() ||
                numActaStr == null || numActaStr.isEmpty() ||
                fechaNacimiento == null) {

            Alerta.mostrarError("Todos los campos son obligatorios.");
            return;
        }

        // Validar número de acta
        int numActa;

        try {
            numActa = Integer.parseInt(numActaStr);

            if (numActa <= 0) {
                Alerta.mostrarError("El número de acta debe ser mayor a 0.");
                return;
            }

        } catch (NumberFormatException e) {
            Alerta.mostrarError("El número de acta debe ser numérico.");
            return;
        }

    }

    @FXML
    private void limpiarFormulario() {
        txtFolio.clear();
        txtNumActa.clear();
        dpFechaNacimiento.setValue(null);
        // ComboBox
        cbDepartamento.setValue(null);
        // Spinner (volver a valores por defecto)
        spHora.getValueFactory().setValue(12);
        spMinuto.getValueFactory().setValue(0);
    }
}
