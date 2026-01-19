public class Main {
    public static void main(String[] args) {
        Durmiente d = new Durmiente();
        try {
            d.start();
            Thread.sleep(3000);
            d.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
