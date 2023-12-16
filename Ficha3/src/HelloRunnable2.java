public class HelloRunnable2 implements  Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + "Eu sou uma thread");
    }

    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Modo de uso: java HelloRunnable2 <nº>");

        } else {
            final int count = Integer.parseInt(args[0]);

            Thread[] t = new Thread[count];

            for(int i = 0; i < count; i++) {
                HelloRunnable2 ex2 = new HelloRunnable2();
                t[i] = new Thread(ex2, "[Th" + i + "]");
                t[i].start();
                System.out.println(Thread.currentThread().getState());

            }
        }
    }
}
