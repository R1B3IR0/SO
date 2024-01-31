package ex1;

import javax.swing.*;

public class Janela implements Runnable {
    protected Monitor m;
    protected int i;

    public Janela(Monitor m, int i) {
        this.m = m;
        this.i = i;
    }

    @Override
    public void run() {
        String  myname = Thread.currentThread().getName();
        JFrame f = new JFrame(myname);
        JLabel l = new JLabel("#");
        f.add(l);
        f.setSize(150, 200);
        f.setLocation(i*200, 100);
        f.setVisible(true);

        /**
         * Wait for the monitor to wake up the thread
         */
        synchronized (m) {
            System.out.println("Thread " + myname + " waiting...");
            m.espera();
            System.out.println("Thread " + myname + " woke up!");
        }

        for(int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {}
            l.setText("" + l.getText() + "#");
        }
        f.dispose();
    }

    public static void main(String[] args) {
        Monitor mon = new Monitor();
        Thread[] ths = new Thread[8];

        for(int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(new Janela(mon, i), "Th" + i);
            ths[i].start();
        }
        System.out.println("[Main] All threads created.");
        System.out.println("[Main] Activating threads!");


        try {
            Thread.sleep(5 * 1000); // Wait 5 seconds to start label
        } catch (InterruptedException ie) {}


        /**
         * Wake up all threads at once
         */
        synchronized (mon) {
            for(int i = 0; i < ths.length; i++) {
                mon.acordaTodas();
                System.out.println("[Main] Thread " + ths[i].getName() + " notified.");
            }
        }

        System.out.println("[Main] All threads notified.");

        try {
            for(int i = 0; i < ths.length; i++) {
                ths[i].join();
            }
        } catch (InterruptedException ie) {}
        System.out.println("[Main] All threads ended!.");
    }
}
