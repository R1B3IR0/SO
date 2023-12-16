package ex3;

import java.util.Random;

public class FirstThread extends Thread {
    @Override
    public void run() {
        System.out.println("[" + getName() + "]: INIT (1)");
        try {
            // Gera um número aleatório entre 1 e 9
            int randomNumber = new Random().nextInt(9) + 1;
            Thread.sleep(randomNumber * 1000); // Converte segundos para milissegundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[" + getName() + "]: END");
    }
}
