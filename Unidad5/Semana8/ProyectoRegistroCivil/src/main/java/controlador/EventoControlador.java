package controlador;

import catalogo.Departamento;
import dao.EventoDAO;
import dao.EventoDAOImpl;
import hilos.HilosEventosFX;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import modelo.*;
import patrones.strategy.GeneradorActaNacimiento;
import util.Alerta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    private EventoDAO eventoDAO;
    private Persona registrador;

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

    public EventoControlador() {
        eventoDAO = new EventoDAOImpl();
    }

    public void setRegistrador(Persona registrador) {
        this.registrador = registrador;
    }

    @FXML
    private void guardarEvento() {
        try {
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
            if (folio.isEmpty() || lugar.isEmpty() || fechaNacimiento == null) {
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
            if (registrador == null) {
                Alerta.mostrarError("Debe registrar primero la persona.");
                return;
            }

            Nacimiento evento = new Nacimiento(
                    folio,
                    numActa,
                    fechaRegistro,
                    registrador,
                    hora,
                    fechaNacimiento,
                    lugar
            );

            evento.setEstrategia(new GeneradorActaNacimiento());

            // Mostrar ProgressIndicator antes de iniciar el hilo
            // --- Aquí activas el ProgressIndicator y bloqueas los controles ---
            progressIndicator.setVisible(true);
            txtFolio.setDisable(true);
            txtNumActa.setDisable(true);
            cbDepartamento.setDisable(true);
            btnGuardarEvento.setDisable(true);

            // Ejecutar procesamiento en hilos para JavaFX
            HilosEventosFX hilosFX = new HilosEventosFX();
            hilosFX.registrarEventoFX(List.of(evento), txtResultado, () -> {
                // Esto se ejecuta en el hilo de UI después de procesar
                listarEventos();
                limpiarFormulario();
                Alerta.mostrarInfo("Evento procesado correctamente");
                progressIndicator.setVisible(false);
                txtFolio.setDisable(false);
                txtNumActa.setDisable(false);
                cbDepartamento.setDisable(false);
                btnGuardarEvento.setDisable(false);
            });

            // Guardar en archivo
            eventoDAO.guardar(evento);

        } catch (Exception e) {
            progressIndicator.setVisible(false);
            txtFolio.setDisable(false);
            txtNumActa.setDisable(false);
            cbDepartamento.setDisable(false);
            btnGuardarEvento.setDisable(false);
            Alerta.mostrarError("Error: " + e.getMessage());
        }
    }

    @FXML
    private void listarEventos() {
        if (registrador == null) {
            Alerta.mostrarError("Debe registrar una persona primero.");
            return;
        }

        List<Evento> lista = eventoDAO.listarPorPersona(registrador.getId());

        StringBuilder sb = new StringBuilder();

        for (Evento e : lista) {
            sb.append("Folio: ").append(e.getFolio())
                    .append(" | Acta: ").append(e.getNumActa())
                    .append(" | Fecha: ").append(e.getFechaReg())
                    .append("\n");
        }

        txtResultado.setText(sb.toString());
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
