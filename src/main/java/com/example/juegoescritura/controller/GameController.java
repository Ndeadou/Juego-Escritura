package com.example.juegoescritura.controller;

import com.example.juegoescritura.model.WordGenerator;

public class GameController {
    private String currentWord;
    private WordGenerator generator;

    public String getCurrentWord() {
        return currentWord;
    }

    public GameController() {
        this.generator = new WordGenerator();
    }

    public void startNewRound(int level) {
        currentWord = generator.generateWord(level);
        System.out.println("Escribe esta palabra: " + currentWord);
    }

    public boolean checkWord(String input) {
        return input.equalsIgnoreCase(currentWord);
    }
}


