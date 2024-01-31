package ex2;

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

        synchronized (m) {
            System.out.println("Thread " + myname + " waiting...");
            m.espera(); // Aguarda a notificação para continuar
            System.out.println("Thread " + myname + " woke up!");
        }

        try {
            Thread.sleep(1000 * i); // Intervalo crescente de 1 segundo
        } catch (InterruptedException ie) {
            ie.printStackTrace();
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

        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(new Janela(mon, i), "Th" + i);
            ths[i].start();
        }

        System.out.println("[Main] All threads created.");
        System.out.println("[Main] Activating threads!");


        try {
            Thread.sleep(5 * 1000); // Aguarda 5 segundos antes de libertar as threads
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[Main] Activating threads!");

        synchronized (mon) {
            for (int i = 0; i < ths.length; i++) {
                mon.acordaTodas(); // Notifica uma thread em espera
                System.out.println("[Main] Thread " + ths[i].getName() + " notified.");
            }
            //System.out.println("[Main] Notifying all threads to start!");
            //mon.notifyAll(); // Notifica todas as threads em espera
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
