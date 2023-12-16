package ex2.mySolution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class MyEx2 implements Runnable {

    Semaphore s1;
    Semaphore s2;
    String fileName;

    public MyEx2(Semaphore sem1, Semaphore sem2, String fileName) {
        this.s1 = sem1;
        this.s2 = sem2;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            String myname = Thread.currentThread().getName();

            if (s2.tryAcquire()) { // Verifica se é possível adquirir o semáforo para as duas linhas
                s1.acquire(); // Adquire o semáforo para uso do recurso
                // Escreve as linhas no arquivo
                writeToFile(myname + ": Escrevendo linha 1");
                Thread.sleep(1000);
                writeToFile(myname + ": Escrevendo linha 2");
                // Libera os semáforos após escrever as duas linhas
                s1.release(); // Libera o semáforo de uso do recurso
                s2.release(); // Libera o semáforo para o limite de linhas
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Modo de uso: java Ex2 <nome do ficheiro>");
            System.exit(1);
        }

        final Semaphore sem1 = new Semaphore(1);
        final Semaphore sem2 = new Semaphore(10);

        String fileName = args[0]; // Nome do arquivo passado como argumento

        Thread[] ths = new Thread[20];

        for (int i = 0; i < 20; i++) {
            ths[i] = new Thread(new MyEx2(sem1, sem2, fileName), "Th" + i);
        }

        for (int i = 0; i < 20; i++) {
            ths[i].start();
        }

        System.out.println("[Main] All threads created!");
    }
}

