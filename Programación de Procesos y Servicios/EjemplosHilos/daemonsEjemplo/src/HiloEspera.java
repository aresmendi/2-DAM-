class HiloEspera extends Thread {
    @Override
    public void run() {
        System.out.println("Soy el "+ Thread.currentThread().getName() +" empezando.");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() +" interrumpido.");
            return;
        }
        System.out.println(Thread.currentThread().getName() +" acabado.");
    }
}
