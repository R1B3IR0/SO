public class Run  extends  Thread {
    public SharedObj share;

    public Run (SharedObj s) {
        share = s;
    }

    public void run() {
        String myname = Thread.currentThread().getName();

        try {
            while (true) {
                if(Thread.interrupted()) return;

                Thread.sleep(1000);
                synchronized (share) {
                    // Aguarda até que o número corresponda ao último dígito do nome
                    while (share.getNumber() != share.getName().charAt(share.getName().length() - 1) - '0') {
                        share.wait();
                    }
                    // Imprime a mensagem e atualiza o número
                    System.out.println("[" + myname + "] Number:" + share.getNumber() + "(" + share.getName() + ")");
                    share.setNumber(share.getNumber() + 1);

                    // Notifica outras threads que aguardam
                    share.notifyAll();
                }
            }
        } catch (InterruptedException e) {}
    }
    public void setShare(SharedObj s) {
        share = s;
    }
}
