package ex1.proffSolution;

import java.util.concurrent.Semaphore;

public class Ex1 implements Runnable {

    Semaphore sem;
    int i;

    public Ex1(Semaphore sem, int i) {
        this.sem = sem;
        this.i = i;
    }

    private void writeToFile(int start, int end) {
        for(int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public void run() {
        try {
            sem.acquire();

            writeToFile(i*200, i*200 + 199);

            sem.release();
        } catch (InterruptedException iex) {}

    }

    public static void main(String[] args) throws InterruptedException {
        final Semaphore sem = new Semaphore(1, true);
        Thread[] ths = new Thread[5];

        for(int i = 0; i < 5; i++) {
            ths[i] = new Thread(new Ex1(sem, i), "Thread " + i);
        }

        for(int i = 0; i < 5; i++) {
            ths[i].start();
        }

        System.out.println("[Main] All threads created!");

        for (int i = 0; i < 5; i++) {
            ths[i].join(); // Espera todas as threads terminarem antes de fechar o arquivo
        }

        sem.release();
    }

    // O recurso partilhado é o ficheiro, que é acedido em exclusão mútua.
}
