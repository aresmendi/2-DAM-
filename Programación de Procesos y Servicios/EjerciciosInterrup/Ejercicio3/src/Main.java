public class Main {
    public static void main(String[] args) {
        Bucle b = new Bucle();
        b.start();
        try {
            Thread.sleep(1000);
            b.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
