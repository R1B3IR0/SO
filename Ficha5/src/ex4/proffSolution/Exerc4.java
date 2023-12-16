package ex4.proffSolution;

public class Exerc4 {
    public static void main(String[] args) {
        CounterSync counter = new CounterSync();

        PrinterThread thread[] = new PrinterThread[10];

        for(int c = 0; c < 10; c++) {
            thread[c] = new PrinterThread(counter);
            thread[c].setName("[TH" + c + "]");
            thread[c].start();
        }


        for(int c = 0; c < 10; c++) {
            try {
                thread[c].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Impressão terminada ");
    }
}
