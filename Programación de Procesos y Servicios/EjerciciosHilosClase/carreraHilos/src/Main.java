public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Corredor("Pelayo"));
        Thread t2 = new Thread(new Corredor("García"));

        t1.start();
        t2.start();
    }
}
