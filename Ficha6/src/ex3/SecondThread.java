package ex3;

import java.util.Random;

public class SecondThread extends Thread {
    private final FirstThread firstThread;

    public SecondThread(FirstThread firstThread) {
        this.firstThread = firstThread;
    }

    @Override
    public void run() {
        try {
            // Aguarda a primeira thread terminar
            firstThread.join();

            //System.out.println("[" + getName() + "]: end");

            // Gera um número aleatório entre 1 e 9 para o número de linhas a serem escritas
            int linesToWrite = new Random().nextInt(9) + 1;
            for (int i = 0; i < linesToWrite; i++) {
                System.out.println("[" + getName() + "]: end");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

