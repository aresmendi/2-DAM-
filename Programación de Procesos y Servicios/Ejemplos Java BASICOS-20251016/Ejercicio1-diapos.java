public class PingPong extends Thread {
    private static final Object lock = new Object();
    private String word;
    private static boolean isPPrinted = false; // Controla el orden de impresión

    public PingPong(String s) {
        word = s;
    }

    public void run() {
        for (int i = 0; i < 3000; i++) {
            synchronized (lock) {
                try {
                    // Si `word` es "P", espera a que `p` haya impreso
                    if (word.equals("P")) {
                        while (!isPPrinted) {
                            lock.wait();
                        }
                    } else {
                        // Si `word` es "p", espera a que `P` haya impreso
                        while (isPPrinted) {
                            lock.wait();
                        }
                    }
                    
                    // Imprime el carácter y cambia el turno
                    System.out.print(word);
                    System.out.flush();
                    
                    // Cambia el estado para alternar entre "P" y "p"
                    isPPrinted = !isPPrinted;
                    lock.notifyAll(); // Despierta al otro hilo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread tP = new PingPong("P");
        Thread tp = new PingPong("p");
        
        tp.start();
        tP.start();
    }
/*
No es necesario importar nada adicional para que este código funcione, ya que las clases y métodos utilizados (Thread, Object, synchronized, wait(), y notify()) están todos en el paquete java.lang, que se importa automáticamente en todos los programas de Java.
*/
}
