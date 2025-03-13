module com.example.juegoescritura {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.juegoescritura to javafx.fxml;
    exports com.example.juegoescritura;
    exports com.example.juegoescritura.controller;
    exports com.example.juegoescritura.model;
    exports com.example.juegoescritura.view;
}