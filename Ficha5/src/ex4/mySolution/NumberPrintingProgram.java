package ex4.mySolution;

import java.util.concurrent.Semaphore;

public class NumberPrintingProgram {
    private static Semaphore semaphore = new Semaphore(1);
    private static int currentValue = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int startValue = i * 100;
            Thread thread = new NumberPrintingThread(startValue);
            thread.start();
        }
    }

    static class NumberPrintingThread extends Thread {
        private int startValue;

        public NumberPrintingThread(int startValue) {
            this.startValue = startValue;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                for (int i = 0; i < 100; i++) {
                    System.out.println(startValue + i);
                }
                currentValue = startValue + 99;
                if (currentValue == 999) {
                    System.out.println("Impressão terminada");
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
