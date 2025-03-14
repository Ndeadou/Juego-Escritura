package com.example.juegoescritura;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Logger;

/**
 * Main class for the fast typing game.
 * Responsible for initializing and displaying the game's graphical interface.
 *
 * @author Miguel Descance
 * @version 1.0
 * @since 2024-03-14
 * @see javafx.application.Application
 */
public class JuegoEscrituraDemo extends Application {

    /**
     * Logger for recording events and errors in the application.
     */
    private static final Logger logger = Logger.getLogger(JuegoEscrituraDemo.class.getName());

    /**
     * JavaFX application startup method.
     * Loads the main view from the FXML file.
     *
     * If an error occurs while loading the FXML file, it is caught and logged.
     *
     * @param stage The main window of the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/juegoescritura/MainView.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Fast Typing Game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            logger.severe("Error loading the main view: " + e.getMessage());
        }
    }

    /**
     * Main method that starts the application.
     * Calls the {@code launch()} method from JavaFX.
     *
     * @param args Command-line arguments.
     * @serialData Launches the execution of the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

