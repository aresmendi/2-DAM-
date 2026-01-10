public class EjercicioHilos2 {
    public static class Contador implements Runnable {
        private int contador;
        private String nombreHilo;
        private int limiteContador;

        public Contador(String nombreHilo, int limiteContador) {
            this.contador = 0;
            this.nombreHilo = nombreHilo;
            this.limiteContador = limiteContador;
        }

        @Override
        public void run() {

            for (int i = contador; i < limiteContador; i++) {
                System.out.println("Hilo: " + nombreHilo + ": " + contador);
                contador++;
            }
            System.out.println("Hilo: " + nombreHilo + ": " + "ya ha acabado");
        }
    }

    public static void main(String[] args) {
        Contador c1 = new Contador("Hilo1", 40);
        Contador c2 = new Contador("Hilo2", 30);
        Contador c3 = new Contador("Hilo3", 20);
        Contador c4 = new Contador("Hilo4", 10);

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);
        Thread t4 = new Thread(c4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
         //Join hace que hasta que no se acaben los hilos de los contadores, no acabe el hilo principal
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Fin de programa");
    }
}
