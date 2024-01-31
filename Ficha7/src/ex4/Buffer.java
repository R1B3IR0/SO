package ex4;

public class Buffer {
    private char conteudo;
    private boolean ocupado = false;

    public synchronized void escrever(char c) {
        while (ocupado) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        conteudo = c;
        ocupado = true;
        System.out.println("Produtor escreveu: " + c);
        notify();
    }

    public synchronized char ler() {
        while (!ocupado) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        char c = conteudo;
        ocupado = false;
        System.out.println("Consumidor leu: " + c);
        notify();
        return c;
    }
}
