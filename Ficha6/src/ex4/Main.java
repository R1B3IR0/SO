package ex4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int THREAD_COUNT = 20;
        CustomThread[] threads = new CustomThread[THREAD_COUNT];

        // Inicializando as 20 threads
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new CustomThread(i + 1);
            threads[i].start();
            threads[i].deactivate(); // Inicialmente, as threads estão suspensas
        }

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("Insira o número da thread para ativar (1-" + THREAD_COUNT + "): ");
            int threadNumber = scanner.nextInt();

            if (threadNumber >= 1 && threadNumber <= THREAD_COUNT) {
                threads[threadNumber - 1].activate(); // Ativa a thread desejada
            } else {
                System.out.println("Número inválido! Insira um número de 1 a " + THREAD_COUNT);
            }
        }
    }
}
