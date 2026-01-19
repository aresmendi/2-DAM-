public class Main {
    public static void main(String[] args) {
        Reloj reloj = new Reloj();
        reloj.isDaemon();
        Contador contador = new Contador();
        reloj.start();
        contador.start();
        try {
            Thread.sleep(1500);
            contador.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
