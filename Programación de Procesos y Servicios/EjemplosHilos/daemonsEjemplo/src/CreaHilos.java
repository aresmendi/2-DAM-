public class CreaHilos {
    public static void main(String[] args) {
        HiloEspera hilo1 = new HiloEspera ();
        HiloEspera hilo2 = new HiloEspera ();
        HiloEspera hilo3 = new HiloEspera ();
        hilo3.setDaemon(true);
        hilo1.setName("hilo 1");
        hilo2.setName("hilo 2");
        hilo3.setName("hilo 3 DAEMON");
        hilo1.start();
        hilo2.start();
        hilo3.start();
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrumpido. ");
            return;
        }
        hilo1.interrupt();
        try {
            hilo3.sleep(20000);
            System.out.println(hilo3.getName() + " ha acabado de verdad");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}