package ex4;

import ex4.Buffer;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread produtorThread = new Thread(new Produtor(buffer));
        Thread consumidorThread = new Thread(new Consumidor(buffer));

        produtorThread.start();
        consumidorThread.start();

        try {
            produtorThread.join();
            consumidorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
