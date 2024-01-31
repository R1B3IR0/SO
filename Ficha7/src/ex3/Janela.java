package ex3;

import javax.swing.*;

public class Janela implements Runnable {
    private final Monitor m;
    private final int i;

    public Janela(Monitor m, int i) {
        this.m = m;
        this.i = i;
    }

    @Override
    public void run() {
        String myname = Thread.currentThread().getName();
        JFrame f = new JFrame(myname);
        JLabel l = new JLabel("#");
        f.add(l);
        f.setSize(150, 200);
        f.setLocation(i * 200, 100);
        f.setVisible(true);

        synchronized (m) {
            System.out.println("Thread " + myname + " waiting...");
            m.acordaProximaThread();
            System.out.println("Thread " + myname + " acordou!");
        }

        try {
            Thread.sleep(1000 * i); // Intervalo crescente de 1 segundo
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            l.setText("" + l.getText() + "#");
        }
        f.dispose();
    }

    public static void main(String[] args) {
        Monitor mon = new Monitor();
        Thread[] ths = new Thread[8];

        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(new Janela(mon, i), "Th" + i);
            ths[i].start();

            try {
                Thread.sleep(1000); // Desfasamento de 1 segundo
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
