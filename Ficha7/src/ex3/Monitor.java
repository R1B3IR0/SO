package ex3;

public class Monitor {

    protected int vez = 0;

    public synchronized void acordaProximaThread() {
        vez++;
        notifyAll();
    }
    public synchronized void acordaTodas() {
        vez++;
        this.notifyAll();
    }

    public synchronized void espera() {
        int minhaVez = Integer.parseInt(Thread.currentThread().getName().substring(2));

        while (minhaVez != vez) {
            try {
                wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}
