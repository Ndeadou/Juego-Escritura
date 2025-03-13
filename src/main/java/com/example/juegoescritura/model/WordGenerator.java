package com.example.juegoescritura.model;

import java.util.Random;

public class WordGenerator {
    private static final String[] LEVEL_1_WORDS = {
            "sol", "luz", "pan", "flor", "mar"
    };

    private static final String[] LEVEL_2_WORDS = {
            "manzana", "elefante", "teclado", "ventana", "juego"
    };

    private static final String[] LEVEL_3_WORDS = {
            "programar", "universo", "computadora", "relámpago", "montaña"
    };

    private final Random random;

    public WordGenerator() {
        this.random = new Random();
    }

    public String generateWord(int level) {
        String[] words;

        switch (level) {
            case 1:
                words = LEVEL_1_WORDS;
                break;
            case 2:
                words = LEVEL_2_WORDS;
                break;
            case 3:
                words = LEVEL_3_WORDS;
                break;
            default:
                words = LEVEL_1_WORDS;
        }

        int index = random.nextInt(words.length);
        String word = words[index];
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}



