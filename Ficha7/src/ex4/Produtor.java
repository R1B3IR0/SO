package ex4;

public class Produtor implements Runnable{
    private Buffer buffer;

    public Produtor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100); // Aguarda 100 milissegundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            char caractere = (char) ('A' + i);
            buffer.escrever(caractere);
        }
    }
}
