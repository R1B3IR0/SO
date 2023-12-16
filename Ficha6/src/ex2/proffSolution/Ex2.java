package ex2.proffSolution;

import java.util.concurrent.Semaphore;

public class Ex2 implements Runnable {
    Semaphore s1;
    Semaphore s2;

    public Ex2(Semaphore sem1, Semaphore sem2) {
        this.s1 = sem1;
        this.s2 = sem2;
    }

    @Override
    public void run() {
        boolean flag = true;

        try {
            String myname = Thread.currentThread().getName();

            while (flag) {
                s1.acquire(); //Significa que o recurso está a ser usado
                if(s2.availablePermits() > 0) {
                    System.out.println("[" + myname + "]: is linha" + s2.availablePermits());

                    s2.acquire();
                }
                else {
                    flag = false;
                }
                s1.release();  //Significa que o recurso está livre
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        /*
        if (args.length < 1) {
            System.err.println("Modo de uso: java Ex2 <nome do ficheiro>");
            System.exit(1);
        }
         */
        final Semaphore sem1 = new Semaphore(1);
        final Semaphore sem2 = new Semaphore(10);

        Thread[] ths = new Thread[20];

        for(int i = 0; i < 20; i++) {
            ths[i] = new Thread(new Ex2(sem1, sem2), "Th" + i);
        }

        for(int i = 0; i < 20; i++) {
            ths[i].start();
        }

        System.out.println("[Main] All threads created!");
    }
}
