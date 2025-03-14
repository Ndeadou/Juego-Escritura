package com.example.juegoescritura.model;

import java.util.Random;

/**
 * Class responsible for generating random words based on the player's level.
 *
 * @author Miguel Descance
 * @version 1.0
 * @since 2024-03-14
 * @see java.util.Random
 */
public class WordGenerator {

    /** List of words for level 1 (short and simple words). */
    private static final String[] LEVEL_1_WORDS = {
            "Sol", "Luz", "Pan", "Flor", "Mar", "Casa", "Luna", "Roca", "Agua", "Paz", "sal", "bus"
    };

    /** List of words for level 2 (medium-length words). */
    private static final String[] LEVEL_2_WORDS = {
            "manzana", "elefante", "teclado", "ventana", "juego", "carretera", "película", "mariposa", "sandía", "espejo", "Higuana"
    };

    /** List of words for level 3 (longer and more complex words). */
    private static final String[] LEVEL_3_WORDS = {
            "programar", "universo", "computadora", "relámpago", "montaña", "astronauta", "fotografía", "bicicleta", "biblioteca", "murciélago"
    };

    /** List of words for level 4 (difficult and less common words). */
    private static final String[] LEVEL_4_WORDS = {
            "hipopotomonstrosesquipedaliofobia", "otorrinolaringología", "paralelepípedo", "electroencefalografista", "anticonstitucionalmente",
            "características", "parietotemporoccipital", "Esternocleidomastoideo", "Hiperventilación", "xilófono",
            "Antipatriota", "Desoxirribonucleótido"
    };

    /** Random number generator for selecting words. */
    private final Random random;

    /**
     * Constructor for the {@code WordGenerator} class.
     * Initializes the random number generator.
     */
    public WordGenerator() {
        this.random = new Random();
    }

    /**
     * Generates a random word based on the specified level.
     *
     * @param level The difficulty level of the game (1 to 4).
     * @return A random word from the corresponding level, with the first letter capitalized.
     * @throws IllegalArgumentException If the level is less than 1.
     * @serialData A random word is obtained from the corresponding list.
     */
    public String generateWord(int level) {
        // Select the list of words based on the level
        String[] words = switch (level) {
            case 1 -> LEVEL_1_WORDS;
            case 2 -> LEVEL_2_WORDS;
            case 3 -> LEVEL_3_WORDS;
            case 4 -> LEVEL_4_WORDS;
            default -> LEVEL_4_WORDS;
        };

        // Get a random word from the selected list
        int index = random.nextInt(words.length);
        String word = words[index];

        // Return the word with the first letter capitalized
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}