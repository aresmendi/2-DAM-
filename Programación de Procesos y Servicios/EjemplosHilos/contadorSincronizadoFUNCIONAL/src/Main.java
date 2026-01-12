public class Main {
    public static void main(String[] args) {
        Contador contador = new Contador(); // UN SOLO CONTADOR

        Hilo h1 = new Hilo("Hilo A", contador);
        Hilo h2 = new Hilo("Hilo B", contador);

        h1.start();
        h2.start();
    }
}
