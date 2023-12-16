package ex5;

import java.util.concurrent.CountDownLatch;

public class MaxValueFinder extends Thread {
    private int[] array;
    private int start;
    private int end;
    private int maxValue = Integer.MIN_VALUE;
    private CountDownLatch latch;

    public MaxValueFinder(int[] array, int start, int end, CountDownLatch latch) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.latch = latch;
    }

    public void run() {
        for (int i = start; i < end; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        latch.countDown();
    }

    public int getMaxValue() {
        return maxValue;
    }
}
