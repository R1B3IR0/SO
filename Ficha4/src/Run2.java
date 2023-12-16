public class Run2  extends Thread {
    public  SharedObj share;
    public int n;

    public Run2(SharedObj s, int i) {
        share = s;
        n= i;
    }

    public void run() {
        String myname = Thread.currentThread().getName();

        try {
            // Aguarda um número específico de segundos
            Thread.sleep(n * 1000);

            synchronized (share) {
                // Aguarda até que o número seja igual a n
                while (n != share.getNumber()) {
                    share.wait();
                }
                // Imprime a mensagem e atualiza o nome
                System.out.println("[" + myname + "] Number:" + n);
                share.setName("share.name definido por: " + myname);
                // Notifica outras threads que aguardam
                share.notifyAll();
            }
        } catch (InterruptedException e) {}

    }
}
