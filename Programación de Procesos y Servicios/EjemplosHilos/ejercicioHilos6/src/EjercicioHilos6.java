public class EjercicioHilos6 {
    public static void main(String[] args) {
        boolean sincronizado = true;
        Counter c = new Counter(sincronizado);

        HiloContador h1 = new HiloContador(1, c, 5);
        HiloContador h2 = new HiloContador(2, c, 10);

        h1.start();
        h2.start();

        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Fin programa");
    }

}
