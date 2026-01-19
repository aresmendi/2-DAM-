public class Main {
    public static void main(String[] args) {
        Corredor c1 = new Corredor("Pepe");
        Corredor c2 = new Corredor("Pepa");
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
