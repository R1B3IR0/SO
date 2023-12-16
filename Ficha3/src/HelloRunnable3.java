public class HelloRunnable3 implements Runnable {

    public void run() {
        System.out.println("Eu sou uma thread: " + Thread.currentThread().getId());
    }

    public static void main(String[] args) throws InterruptedException {
        if(args.length != 1){
            System.out.println("Modo de uso: java HelloRunnable2 <nº>");

        } else {
            final int seconds = Integer.parseInt(args[0]);
            int lines = 10;

            (new Thread(new HelloRunnable())).start();

            for(int i = 0; i < lines; i++) {
                Thread.sleep(seconds * 1000);
                System.out.println("Linha: " + i);
            }
        }
    }
}
