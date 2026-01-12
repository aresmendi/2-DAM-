public class Main {
    public static void main(String[] args) {
        Autoguardado a = new Autoguardado();
        //Importante seteamos a Daemon antes de darle a start()
        a.setDaemon(true);
        a.start();
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Usuario escribiendo...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Documento cerrado");
    }
}
