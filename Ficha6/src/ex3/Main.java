package ex3;

public class Main {
    public static void main(String[] args) {
        FirstThread thread1 = new FirstThread();
        SecondThread thread2 = new SecondThread(thread1);

        thread1.start();
        thread2.start();
    }
}
