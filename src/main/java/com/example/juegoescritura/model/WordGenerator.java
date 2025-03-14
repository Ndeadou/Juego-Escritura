package com.example.juegoescritura.model;

import java.util.Random;

/**
 * Clase encargada de generar palabras aleatorias según el nivel del jugador.
 *
 * @author Miguel Descance
 * @version 1.0
 * @since 2024-03-14
 * @see java.util.Random
 */
public class WordGenerator {

    /** Lista de palabras para el nivel 1 (palabras cortas y sencillas). */
    private static final String[] LEVEL_1_WORDS = {
            "Sol", "Luz", "Pan", "Flor", "Mar", "Casa", "Luna", "Roca", "Agua", "Paz", "sal", "bus"
    };

    /** Lista de palabras para el nivel 2 (palabras medianas). */
    private static final String[] LEVEL_2_WORDS = {
            "manzana", "elefante", "teclado", "ventana", "juego", "carretera", "película", "mariposa", "sandía", "espejo", "Higuana"
    };

    /** Lista de palabras para el nivel 3 (palabras más largas y complejas). */
    private static final String[] LEVEL_3_WORDS = {
            "programar", "universo", "computadora", "relámpago", "montaña", "astronauta", "fotografía", "bicicleta", "biblioteca", "murciélago"
    };

    /** Lista de palabras para el nivel 4 (palabras difíciles y poco comunes). */
    private static final String[] LEVEL_4_WORDS = {
            "hipopotomonstrosesquipedaliofobia", "otorrinolaringología", "paralelepípedo", "electroencefalografista", "anticonstitucionalmente",
            "características", "parietotemporoccipital", "Esternocleidomastoideo", "Hiperventilación", "xilófono",
            "Antipatriota", "Desoxirribonucleótido"
    };

    /** Generador de números aleatorios para seleccionar palabras. */
    private final Random random;

    /**
     * Constructor de la clase WordGenerator.
     * Inicializa el generador de números aleatorios.
     */
    public WordGenerator() {
        this.random = new Random();
    }

    /**
     * Genera una palabra aleatoria según el nivel especificado.
     *
     * @param level El nivel de dificultad del juego (1 a 4).
     * @return Una palabra aleatoria del nivel correspondiente, con la primera letra en mayúscula.
     * @throws IllegalArgumentException Si el nivel es menor a 1.
     * @serialData Se obtiene una palabra aleatoria de la lista correspondiente.
     */
    public String generateWord(int level) {
        // Selecciona la lista de palabras según el nivel
        String[] words = switch (level) {
            case 1 -> LEVEL_1_WORDS;
            case 2 -> LEVEL_2_WORDS;
            case 3 -> LEVEL_3_WORDS;
            case 4 -> LEVEL_4_WORDS;
            default -> LEVEL_4_WORDS;
        };

        // Obtiene una palabra aleatoria dentro de la lista seleccionada
        int index = random.nextInt(words.length);
        String word = words[index];

        // Retorna la palabra con la primera letra en mayúscula
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}






