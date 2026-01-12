public class Main {
    public static void main(String[] args) throws InterruptedException {
        MsLunch msLunch = new MsLunch();

        Hilo h1 = new Hilo("Hilo A", msLunch);
        Hilo h2 = new Hilo("Hilo B", msLunch);

        h1.start();
        h2.start();

        h1.join();
        h2.join();

        System.out.println("c1 = " + msLunch.getC1());
        System.out.println("c2 = " + msLunch.getC2());
    }
}
