public class Main {
    public static void main(String[] args) {
        Thread h1 = new Thread(new HiloConNombre("Pepe"));
        Thread h2 = new Thread(new HiloConNombre("Mariano"));
        Thread h3 = new Thread(new HiloConNombre("Andrés"));

        h1.start();
        h2.start();
        h3.start();
    }
}
