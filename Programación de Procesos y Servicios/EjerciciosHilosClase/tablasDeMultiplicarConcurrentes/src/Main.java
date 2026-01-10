public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new TablaMultiplicar(2));
        Thread t2 = new Thread(new TablaMultiplicar(3));
        Thread t3 = new Thread(new TablaMultiplicar(4));
        t1.start();
        t2.start();
        t3.start();
    }
}
