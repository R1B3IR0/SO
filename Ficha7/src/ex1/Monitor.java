package ex1;

public class Monitor {
    public synchronized void acordaTodas() {
        this.notifyAll();
    }

    public synchronized void espera() {
        try {
            this.wait();
        } catch (InterruptedException ie) {}
    }
}
