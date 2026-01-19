public class Main {
    public static void main(String[] args) {
        Contador c = new Contador();
        try {
            c.start();
            Thread.sleep(500);
            c.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
