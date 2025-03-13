package com.example.juegoescritura.view;

import com.example.juegoescritura.controller.GameController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController controller = new GameController();

        System.out.println("¡Bienvenido al juego de escritura rápida!");
        boolean playAgain = true;

        while (playAgain) {
            System.out.print("Elige un nivel de dificultad (1, 2, 3): ");
            int level = 1;
            try {
                level = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida, se usará nivel 1 por defecto.");
            }

            controller.startNewRound(level);
            System.out.print("Escribe la palabra: ");

            String input = scanner.nextLine();

            if (controller.checkWord(input)) {
                System.out.println("¡Correcto!");
            } else {
                System.out.println("Incorrecto. Inténtalo de nuevo.");
            }

            System.out.print("¿Jugar otra ronda? (s/n): ");
            String response = scanner.nextLine();
            playAgain = response.equalsIgnoreCase("s");
        }

        System.out.println("Gracias por jugar. ¡Hasta luego!");
        scanner.close();
    }
}

