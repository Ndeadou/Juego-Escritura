package com.example.juegoescritura.controller;

import com.example.juegoescritura.model.WordGenerator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Controlador de la interfaz gráfica del juego de escritura rápida.
 * Gestiona la lógica del juego, la actualización de la interfaz y la interacción del usuario.
 *
 * @author Miguel Descance
 * @version 1.0
 * @since 2024-03-14
 * @see javafx.fxml.FXML
 */
public class MainViewController {

    // Elementos de la interfaz gráfica
    @FXML
    private Label wordLabel; // Muestra la palabra a escribir

    @FXML
    private TextField inputField; // Campo donde el usuario escribe la palabra

    @FXML
    private Label resultLabel; // Muestra si la palabra ingresada es correcta o incorrecta

    @FXML
    private Label timerLabel; // Muestra el tiempo restante

    @FXML
    private Label scoreLabel; // Muestra la puntuación del usuario

    @FXML
    private Label levelLabel; // Muestra el nivel actual del jugador

    @FXML
    private ImageView sun1; // Imagen que representa las vidas restantes

    @FXML
    private Button restartButton; // Botón para reiniciar el juego

    // Variables de control del juego
    private WordGenerator wordGenerator; // Generador de palabras aleatorias
    private Timeline timeline; // Temporizador del juego
    private int timeLeft; // Tiempo restante en la ronda
    private int score; // Puntos acumulados
    private int level; // Nivel del jugador
    private int lives; // Vidas restantes del jugador

    // Lista de imágenes que representan las vidas
    private final String[] sunImages = {
            "/com/example/juegoescritura/sol_1.jpeg",
            "/com/example/juegoescritura/sol_2.jpeg",
            "/com/example/juegoescritura/sol_3.jpeg",
            "/com/example/juegoescritura/sol_4.jpeg",
            "/com/example/juegoescritura/sol_5.jpeg"
    };

    /**
     * Método de inicialización del controlador.
     * Se ejecuta automáticamente al cargar el archivo FXML.
     */
    public void initialize() {
        wordGenerator = new WordGenerator();
        restartButton.setVisible(false);
        restartButton.setOnAction(event -> restartGame());

        updateSunImages();
        startGame();
    }

    /**
     * Inicia una nueva partida reiniciando todas las variables del juego.
     */
    private void startGame() {
        score = 0;
        level = 1;
        lives = 5;
        timeLeft = 20;
        inputField.setDisable(false);
        restartButton.setVisible(false);
        updateUI();
        startNewRound();

        inputField.setOnAction(event -> checkWord());
    }

    /**
     * Inicia una nueva ronda generando una nueva palabra y reiniciando el temporizador.
     */
    @FXML
    private void startNewRound() {
        System.out.println("Nueva ronda iniciada!");
        String newWord = wordGenerator.generateWord(level);
        wordLabel.setText(newWord);
        resultLabel.setText("");
        inputField.clear();
        resetTimer();
    }

    /**
     * Verifica si la palabra ingresada es correcta y actualiza el juego en consecuencia.
     */
    @FXML
    private void checkWord() {
        String typedWord = inputField.getText().trim();
        String currentWord = wordLabel.getText().trim();

        if (typedWord.equals(currentWord)) {
            resultLabel.setText("¡Correcto!");
            score++;
            levelUp();
            timeline.stop();
            startNewRound();
        } else {
            resultLabel.setText("Incorrecto, inténtalo de nuevo.");
            decreaseLife();
        }
    }

    /**
     * Reinicia y comienza el temporizador para la ronda actual.
     */
    private void resetTimer() {
        if (timeline != null) {
            timeline.stop();
        }

        timeLeft = Math.max(20 - ((level - 1) / 5) * 2, 2);
        timerLabel.setText("Tiempo restante: " + timeLeft + "s");

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeLeft--;
            timerLabel.setText("Tiempo restante: " + timeLeft + "s");

            if (timeLeft <= 0) {
                timeline.stop();
                handleTimeUp();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Maneja la situación cuando el tiempo se agota.
     */
    private void handleTimeUp() {
        resultLabel.setText("¡Tiempo agotado! Nivel alcanzado: " + level + ". Puntos: " + score);
        decreaseLife();
    }

    /**
     * Reduce la cantidad de vidas del jugador y actualiza la interfaz.
     * Si el jugador pierde todas las vidas, el juego termina.
     */
    private void decreaseLife() {
        if (lives > 0) {
            lives--; // Reducimos la vida solo si quedan
        }

        updateSunImages(); // Actualizar la imagen del sol según la vida actual

        if (lives > 0) {
            startNewRound();
        } else {
            resultLabel.setText("¡Juego terminado! Nivel final: " + level + ". Puntos: " + score);
            inputField.setDisable(true);
            restartButton.setVisible(true);
        }
    }

    /**
     * Aumenta el nivel del jugador y actualiza la interfaz.
     */
    private void levelUp() {
        level++;
        scoreLabel.setText("Puntos: " + score);
        levelLabel.setText("Nivel: " + level);
    }

    /**
     * Actualiza los elementos de la interfaz al inicio del juego.
     */
    private void updateUI() {
        timerLabel.setText("Tiempo restante: 20s");
        scoreLabel.setText("Puntos: 0");
        levelLabel.setText("Nivel: 1");
        updateSunImages();
    }

    /**
     * Actualiza la imagen del sol según la cantidad de vidas restantes.
     */
    private void updateSunImages() {
        if (lives > 0 && lives <= sunImages.length) {
            String imagePath = sunImages[lives - 1]; // Obtener la imagen correcta
            java.net.URL imageUrl = getClass().getResource(imagePath);

            if (imageUrl != null) {
                Image sunImage = new Image(imageUrl.toString());
                sun1.setImage(sunImage);
            } else {
                System.out.println("Advertencia: No se encontró la imagen " + imagePath);
                sun1.setImage(null);
            }
        } else {
            sun1.setImage(null); // Si no hay vidas, ocultamos la imagen
        }
    }

    /**
     * Reinicia el juego llamando al método {@link #startGame()}.
     */
    @FXML
    private void restartGame() {
        startGame();
    }
}























