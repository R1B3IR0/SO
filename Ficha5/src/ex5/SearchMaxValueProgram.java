package ex5;

import java.util.concurrent.CountDownLatch;

public class SearchMaxValueProgram {
    public static void main(String[] args) {
        int[] array = new int[1000];
        CountDownLatch latch = new CountDownLatch(5);

        // Inicialize o array com valores (pode ser aleatório)
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 1000);
        }

        int segmentSize = array.length / 5;
        MaxValueFinder[] threads = new MaxValueFinder[5];

        for (int i = 0; i < 5; i++) {
            int start = i * segmentSize;
            int end = (i == 4) ? array.length : (i + 1) * segmentSize;
            threads[i] = new MaxValueFinder(array, start, end, latch);
            threads[i].start();
        }

        try {
            latch.await(); // Aguarde até que todas as threads tenham terminado
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 5; i++) {
            int threadMax = threads[i].getMaxValue();
            if (threadMax > max) {
                max = threadMax;
            }
        }

        System.out.println("Maior valor encontrado: " + max);
    }
}


