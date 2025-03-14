package com.example.juegoescritura;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Logger;

/**
 * Clase principal del juego de escritura rápida.
 * Se encarga de inicializar y mostrar la interfaz gráfica del juego.
 *
 * @author Miguel Descance
 * @version 1.0
 * @since 2024-03-14
 * @see javafx.application.Application
 */
public class JuegoEscrituraDemo extends Application {

    /**
     * Logger para registrar eventos y errores en la aplicación.
     */
    private static final Logger logger = Logger.getLogger(JuegoEscrituraDemo.class.getName());

    /**
     * Método de inicio de la aplicación JavaFX.
     * Carga la vista principal desde el archivo FXML.
     *
     * Si ocurre un error al cargar el archivo FXML, se captura y se registra en el logger.
     *
     * @param stage La ventana principal de la aplicación.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/juegoescritura/MainView.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Juego de Escritura Rápida");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            logger.severe("Error al cargar la vista principal: " + e.getMessage());
        }
    }

    /**
     * Método principal que inicia la aplicación.
     * Llama al método {@code launch()} de JavaFX.
     *
     * @param args Argumentos de la línea de comandos.
     * @serialData Lanza la ejecución de la aplicación JavaFX.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
