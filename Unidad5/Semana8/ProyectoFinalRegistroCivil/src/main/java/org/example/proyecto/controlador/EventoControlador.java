package org.example.proyecto.controlador;

import org.example.proyecto.catalogo.Departamento;
import org.example.proyecto.dao.EventoDAO;
import org.example.proyecto.dao.EventoDAOImpl;
import org.example.proyecto.hilos.HilosEventosFX;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyecto.modelo.*;
import org.example.proyecto.patrones.strategy.GeneradorActaNacimiento;
import org.example.proyecto.util.Alerta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controlador encargado de la gestión de eventos de registro civil.
 * <p>
 * Permite crear, listar y gestionar eventos de nacimiento,
 * aplicando validaciones, persistencia y procesamiento en hilos JavaFX.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class EventoControlador {

    /** Campo de texto para el folio del evento */
    @FXML private TextField txtFolio;

    /** Campo de texto para el número de acta */
    @FXML private TextField txtNumActa;

    /** Selector de fecha de nacimiento */
    @FXML private DatePicker dpFechaNacimiento;

    /** Área de texto para mostrar resultados */
    @FXML private TextArea txtResultado;

    /** Botón para guardar el evento */
    @FXML private Button btnGuardarEvento;

    /** Spinner para seleccionar la hora */
    @FXML private Spinner<Integer> spHora;

    /** Spinner para seleccionar los minutos */
    @FXML private Spinner<Integer> spMinuto;

    /** ComboBox para seleccionar el departamento */
    @FXML private ComboBox<Departamento> cbDepartamento;

    /** Indicador de progreso durante el procesamiento */
    @FXML private ProgressIndicator progressIndicator;

    /** Acceso a datos de eventos */
    private EventoDAO eventoDAO;

    /** Persona que registra el evento */
    private Persona registrador;

    /** Formato de hora */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

    /**
     * Inicializa los componentes de la interfaz.
     * Configura spinners, comboBox y restricciones de fechas.
     */
    @FXML
    private void initialize() {
        spHora.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12)
        );

        spMinuto.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0)
        );

        cbDepartamento.getItems().addAll(Departamento.values());

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

    /**
     * Constructor del controlador.
     * Inicializa la implementación del DAO.
     */
    public EventoControlador() {
        eventoDAO = new EventoDAOImpl();
    }

    /**
     * Asigna la persona que está registrando el evento.
     *
     * @param registrador persona registradora
     */
    public void setRegistrador(Persona registrador) {
        this.registrador = registrador;
    }

    /**
     * Guarda un nuevo evento de nacimiento.
     * <p>
     * Valida los datos, crea el objeto Evento,
     * lo procesa en un hilo JavaFX y lo persiste en archivo.
     * </p>
     */
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

            if (dep == null) {
                Alerta.mostrarError("Seleccione un departamento.");
                return;
            }

            String lugar = dep.name();

            if (folio.isEmpty() || lugar.isEmpty() || fechaNacimiento == null) {
                Alerta.mostrarError("Todos los campos son obligatorios.");
                return;
            }

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

            progressIndicator.setVisible(true);
            txtFolio.setDisable(true);
            txtNumActa.setDisable(true);
            cbDepartamento.setDisable(true);
            btnGuardarEvento.setDisable(true);

            HilosEventosFX hilosFX = new HilosEventosFX();
            hilosFX.registrarEventoFX(List.of(evento), txtResultado, () -> {
                listarEventos();
                limpiarFormulario();
                Alerta.mostrarInfo("Evento procesado correctamente");
                progressIndicator.setVisible(false);
                txtFolio.setDisable(false);
                txtNumActa.setDisable(false);
                cbDepartamento.setDisable(false);
                btnGuardarEvento.setDisable(false);
            });

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

    /**
     * Lista los eventos registrados de la persona actual.
     */
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

    /**
     * Limpia todos los campos del formulario.
     */
    @FXML
    private void limpiarFormulario() {
        txtFolio.clear();
        txtNumActa.clear();
        dpFechaNacimiento.setValue(null);
        cbDepartamento.setValue(null);
        spHora.getValueFactory().setValue(12);
        spMinuto.getValueFactory().setValue(0);
    }
}