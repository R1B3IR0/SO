package ex2;

public class Counter {
    long count = 0;

    // "synchronized" is a keyword that prevents two threads from accessing the same method at the same time.
    public synchronized void add(long value) {
        this.count += value;
    }

    public String getCount() {
        return String.valueOf(this.count);
    }


    public static class CounterThread extends Thread {
        protected Counter counter = null;

        public  CounterThread(Counter counter) {
            this.counter = counter;
        }

        public void run() {
            for(int i = 0; i < 10; i++) {
                counter.add(i);
            }
        }
    }
}
