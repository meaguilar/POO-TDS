module ProyectoRegistroCivil{
    requires javafx.controls;
    requires javafx.fxml;

    exports org.example.proyecto.controlador;
    exports org.example.proyecto.modelo;

    opens org.example.proyecto.controlador to javafx.fxml;
    opens org.example.proyecto to javafx.graphics;
}