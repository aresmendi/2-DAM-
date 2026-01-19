public class Main {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta("ABC");

        Thread c1 = new Thread(new Cliente("Mario",cuenta));
        Thread c2 = new Thread(new Cliente("Pedro",cuenta));
        c1.start();
        c2.start();
        try {
            c1.join();
            c2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El saldo final es " + cuenta.getSaldo());
    }
}
