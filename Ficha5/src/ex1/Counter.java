package ex1;

public class Counter {
    long count = 0;

    public void add(long value) {
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
                try {
                    Thread.sleep(100); // Adiciona um atraso de 100 milissegundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter.add(i);
            }
        }
    }
}
