public class Main {
    public static void main(String[] args) {
        Lista l = new Lista();
        Demonio d = new Demonio(l);
        d.setDaemon(true);
        d.start();
        for (int i = 0; i < 20; i++) {
            try {
                l.agregar();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(l.getNumeros());
    }
}
