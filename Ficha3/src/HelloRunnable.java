public class HelloRunnable implements Runnable{


    public void run() {
        System.out.println("Eu sou uma Thread");

    }

    public static void main(String[] args) {

        if(args.length != 1) {
            System.out.println("Modo de uso: java HelloRunnable <nº>");
        } else {
            final int count = Integer.parseInt(args[0]); //parse

            Thread[] t = new Thread[count];  //Instância para nova Thread;

            for(int i = 0; i < count; i++){
                HelloRunnable ex1 = new HelloRunnable();  //New Object do tipo HelloRunnable;
                t[i] = new Thread(ex1);
                t[i].start();
            }
        }
    }
}