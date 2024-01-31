package ex4;


public class Consumidor implements Runnable{
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            char caractere = buffer.ler();
        }
    }
}
