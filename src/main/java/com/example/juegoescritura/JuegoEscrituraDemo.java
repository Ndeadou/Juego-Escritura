package com.example.juegoescritura;

import com.example.juegoescritura.controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JuegoEscrituraDemo extends Application {

    private GameController controller;
    private Label wordLabel;
    private TextField inputField;
    private Label resultLabel;
    private Label timerLabel;
    private Label scoreLabel;
    private Label levelLabel;
    private ProgressBar sunProgress;
    private Timeline timeline;
    private int timeLeft;
    private int score;
    private int level;
    private int attempts;
    private int baseTime = 20;

    @Override
    public void start(Stage stage) {
        controller = new GameController();
        score = 0;
        level = 1;
        attempts = 3;

        wordLabel = new Label();
        inputField = new TextField();
        resultLabel = new Label();
        timerLabel = new Label();
        scoreLabel = new Label("Puntos: 0");
        levelLabel = new Label("Nivel: 1");
        sunProgress = new ProgressBar(1.0);
        Button newRoundButton = new Button("Nueva palabra");

        newRoundButton.setOnAction(e -> startNewRound());
        inputField.setOnAction(e -> checkWord());

        VBox layout = new VBox(10, wordLabel, inputField, resultLabel, timerLabel, scoreLabel, levelLabel, sunProgress, newRoundButton);
        Scene scene = new Scene(layout, 400, 350);

        stage.setTitle("Juego de Escritura Rápida");
        stage.setScene(scene);
        stage.show();

        startNewRound();
    }

    private void startNewRound() {
        if (attempts > 0) {
            controller.startNewRound(level);
            wordLabel.setText("Escribe esta palabra: " + controller.getCurrentWord());
            inputField.clear();
            resultLabel.setText("");
            inputField.setDisable(false);
            startTimer(getAdjustedTime());
        }
    }

    private void startTimer(int duration) {
        timeLeft = duration;
        timerLabel.setText("Tiempo restante: " + timeLeft + "s");

        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeLeft--;
            timerLabel.setText("Tiempo restante: " + timeLeft + "s");

            if (timeLeft <= 0) {
                handleTimeOut();
            }
        }));

        timeline.setCycleCount(duration);
        timeline.play();
    }

    private void handleTimeOut() {
        timeline.stop();
        inputField.setDisable(true);
        resultLabel.setText("¡Tiempo agotado! Presiona 'Nueva palabra' para continuar.");
        reduceSunProgress();
    }

    private void checkWord() {
        if (timeLeft > 0) {
            String input = inputField.getText();
            if (controller.checkWord(input)) {
                resultLabel.setText("¡Correcto!");
                timeline.stop();
                inputField.setDisable(true);
                score++;
                scoreLabel.setText("Puntos: " + score);

                if (score % 5 == 0) {
                    level++;
                    levelLabel.setText("Nivel: " + level);
                }
            } else {
                resultLabel.setText("Incorrecto. Inténtalo de nuevo.");
                reduceSunProgress();
            }
        }
    }

    private void reduceSunProgress() {
        attempts--;
        sunProgress.setProgress((double) attempts / 3);

        if (attempts <= 0) {
            endGame();
        }
    }

    private void endGame() {
        timeline.stop();
        inputField.setDisable(true);
        resultLabel.setText("Juego terminado. Puntos: " + score + " | Niveles completados: " + level);
    }

    private int getAdjustedTime() {
        int reducedTime = baseTime - (level / 5) * 2;
        return Math.max(reducedTime, 2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

