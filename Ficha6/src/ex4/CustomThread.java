package ex4;

import java.util.Scanner;

public class CustomThread extends Thread {
    private boolean active;

    public CustomThread(int threadNumber) {
        super("Thread " + threadNumber);
        this.active = false;
    }

    public synchronized void activate() {
        active = true;
        notify(); // Notifica a thread que está esperando
    }

    public synchronized void deactivate() {
        active = false;
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                while (!active) {
                    wait(); // Aguarda a ativação da thread
                }
                System.out.println(getName() + ": Estou ativa!");
                Thread.sleep(3000); // Espera 3 segundos antes de escrever novamente
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

