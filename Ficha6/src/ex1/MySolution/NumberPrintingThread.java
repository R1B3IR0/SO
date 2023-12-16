package ex1.MySolution;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;

public class NumberPrintingThread implements Runnable {
    Semaphore sem;
    int n;
    String fileName;

    public NumberPrintingThread(Semaphore sem, int n, String fileName) {
        this.sem = sem;
        this.n = n;
        this.fileName = fileName;
    }

    public void run() {
        String myName = Thread.currentThread().getName();
        System.out.println("[" + myName + "] Inicio da Thread");

        try {
            sem.acquire(); // Adquira a permissão antes de escrever no arquivo

            PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));

            for (int i = 1; i <= 200; i++) {
                writer.println("Thread " + myName + ": " + i);
            }

            writer.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            sem.release(); // Liberta a permissão, mesmo em caso de exceção
            System.out.println("[" + myName + "] Fim da Thread");
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Modo de uso: java ex1.Runnable <nome do ficheiro>");
            System.exit(1);
        }

        String fileName = args[0];
        Semaphore sem = new Semaphore(1); // Inicialize o Semaphore com 1 para garantir exclusão mútua

        for (int i = 0; i < 5; i++) {
            Thread th = new Thread(new NumberPrintingThread(sem, i, fileName), "Th" + i);
            th.start();
        }
    }
}