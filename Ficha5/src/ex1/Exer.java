package ex1;

public class Exer {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        long startTime = System.currentTimeMillis();

        Thread threadA = new Counter.CounterThread(counter);
        Thread threadB = new Counter.CounterThread(counter);
        threadA.start();
        threadB.start();
        threadA.join(); //Este método é usado para iniciar e aguardar o término da thread, garantindo que o resultado final seja impresso somente após a conclusão da thread.
        threadB.join(); //This method waits until the thread on which it is called terminates.

        long endTime = System.currentTimeMillis();
        System.out.println("Resultado final:" + counter.getCount());
        System.out.println("Tempo de execução com Thread.sleep: " + (endTime - startTime) + " milissegundos");
    }
}
