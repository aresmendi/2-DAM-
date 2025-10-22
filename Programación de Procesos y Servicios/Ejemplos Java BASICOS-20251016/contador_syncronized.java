public class ContadorConcurrente {
    // Número de hilos que se ejecutarán en paralelo
    private static final int NUM_THREADS = 10;
    // Número de incrementos que realizará cada hilo
    private static final int INCREMENTOS_POR_HILO = 100;
    // Contador que será incrementado por los hilos
    private static int contador = 0;

    // Método sincronizado para incrementar el contador
    public synchronized static void incrementarContador() {
        contador++;
    }

    // Clase interna ContadorHilo que extiende Thread y realiza incrementos en el contador
    static class ContadorHilo extends Thread {
        @Override
        public void run() {
            // Llama al método sincronizado una cantidad específica de veces
            for (int i = 0; i < INCREMENTOS_POR_HILO; i++) {
                incrementarContador();  // Incrementa el contador de forma segura
            }
        }
    }

    public static void main(String[] args) {
        // Array para almacenar los hilos
        ContadorHilo[] threads = new ContadorHilo[NUM_THREADS];

        // Crear e iniciar cada hilo
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new ContadorHilo();
            threads[i].start();  // Inicia el hilo
        }

        // Esperar a que todos los hilos terminen su ejecución
        for (ContadorHilo thread : threads) {
            try {
                thread.join();  // Espera a que el hilo actual termine
            } catch (InterruptedException e) {
                e.printStackTrace();  // Manejo de posibles excepciones
            }
        }

        // Mostrar el valor final del contador
        System.out.println("El valor final del contador es: " + contador);
    }
}
