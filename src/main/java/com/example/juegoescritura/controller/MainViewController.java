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
 * Controller for the graphical interface of the fast typing game.
 * Manages the game logic, UI updates, and user interaction.
 *
 * @author Miguel Descance
 * @version 1.0
 * @since 2025-03-14
 * @see javafx.fxml.FXML
 */
public class MainViewController {

    // UI elements
    @FXML
    private Label wordLabel; // Displays the word to be typed

    @FXML
    private TextField inputField; // Input field where the user types the word

    @FXML
    private Label resultLabel; // Displays whether the typed word is correct or incorrect

    @FXML
    private Label timerLabel; // Displays the remaining time

    @FXML
    private Label scoreLabel; // Displays the user's score

    @FXML
    private Label levelLabel; // Displays the current level of the player

    @FXML
    private ImageView sun1; // Image representing the remaining lives

    @FXML
    private Button restartButton; // Button to restart the game

    // Game control variables
    private WordGenerator wordGenerator; // Generates random words
    private Timeline timeline; // Game timer
    private int timeLeft; // Remaining time in the round
    private int score; // Accumulated points
    private int level; // Player's level
    private int lives; // Player's remaining lives

    // List of images representing lives
    private final String[] sunImages = {
            "/com/example/juegoescritura/sol_1.jpeg",
            "/com/example/juegoescritura/sol_2.jpeg",
            "/com/example/juegoescritura/sol_3.jpeg",
            "/com/example/juegoescritura/sol_4.jpeg",
            "/com/example/juegoescritura/sol_5.jpeg"
    };

    /**
     * Initializes the controller.
     * This method is automatically executed when the FXML file is loaded.
     */
    public void initialize() {
        wordGenerator = new WordGenerator();
        restartButton.setVisible(false);
        restartButton.setOnAction(event -> restartGame());

        updateSunImages();
        startGame();
    }

    /**
     * Starts a new game by resetting all game variables.
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
     * Starts a new round by generating a new word and resetting the timer.
     */
    @FXML
    private void startNewRound() {
        System.out.println("New round started!");
        String newWord = wordGenerator.generateWord(level);
        wordLabel.setText(newWord);
        resultLabel.setText("");
        inputField.clear();
        resetTimer();
    }

    /**
     * Checks if the typed word is correct and updates the game accordingly.
     */
    @FXML
    private void checkWord() {
        String typedWord = inputField.getText().trim();
        String currentWord = wordLabel.getText().trim();

        if (typedWord.equals(currentWord)) {
            resultLabel.setText("Correct!");
            score++;
            levelUp();
            timeline.stop();
            startNewRound();
        } else {
            resultLabel.setText("Incorrect, try again.");
            decreaseLife();
        }
    }

    /**
     * Resets and starts the timer for the current round.
     */
    private void resetTimer() {
        if (timeline != null) {
            timeline.stop();
        }

        timeLeft = Math.max(20 - ((level - 1) / 5) * 2, 2);
        timerLabel.setText("Time remaining: " + timeLeft + "s");

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeLeft--;
            timerLabel.setText("Time remaining: " + timeLeft + "s");

            if (timeLeft <= 0) {
                timeline.stop();
                handleTimeUp();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Handles the situation when time runs out.
     */
    private void handleTimeUp() {
        resultLabel.setText("Time's up! Level reached: " + level + ". Points: " + score);
        decreaseLife();
    }

    /**
     * Reduces the player's lives and updates the UI.
     * If the player loses all lives, the game ends.
     */
    private void decreaseLife() {
        if (lives > 0) {
            lives--; // Reduce life only if available
        }

        updateSunImages(); // Update the sun image according to the remaining lives

        if (lives > 0) {
            startNewRound();
        } else {
            resultLabel.setText("Game over! Final level: " + level + ". Points: " + score);
            inputField.setDisable(true);
            restartButton.setVisible(true);
        }
    }

    /**
     * Increases the player's level and updates the UI.
     */
    private void levelUp() {
        level++;
        scoreLabel.setText("Points: " + score);
        levelLabel.setText("Level: " + level);
    }

    /**
     * Updates UI elements at the beginning of the game.
     */
    private void updateUI() {
        timerLabel.setText("Time remaining: 20s");
        scoreLabel.setText("Points: 0");
        levelLabel.setText("Level: 1");
        updateSunImages();
    }

    /**
     * Updates the sun image according to the remaining number of lives.
     */
    private void updateSunImages() {
        if (lives > 0 && lives <= sunImages.length) {
            String imagePath = sunImages[lives - 1]; // Get the correct image
            java.net.URL imageUrl = getClass().getResource(imagePath);

            if (imageUrl != null) {
                Image sunImage = new Image(imageUrl.toString());
                sun1.setImage(sunImage);
            } else {
                System.out.println("Warning: Image not found " + imagePath);
                sun1.setImage(null);
            }
        } else {
            sun1.setImage(null); // Hide the image if no lives remain
        }
    }

    /**
     * Restarts the game by calling the {@link #startGame()} method.
     */
    @FXML
    private void restartGame() {
        startGame();
    }
}
























