public class doThreadSharing {
    public static void main(String[] args) {
        // Cria um objeto compartilhado
        SharedObj share = new SharedObj();

        // Cria uma thread para impressão
        Thread tarefa = new Thread(new Run(share), "Printer_thread");
        tarefa.start();

        // Cria 10 threads numeradas para modificar o objeto compartilhado
        for (int i = 0; i < 10; i++) {
            Thread tarefa2 = new Thread(new Run2(share, i));
            tarefa2.setName("Numbered thread:" + i);
            tarefa2.start();

        }
        
        try {
            // Aguarda as threads terminarem por 11 segundos
            Thread.sleep(11000);
            tarefa.interrupt();
        } catch (InterruptedException e) {}
    }
}
