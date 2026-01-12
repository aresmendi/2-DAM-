public class Hilo extends Thread {
    private String nombre;
    private MsLunch msLunch;
    public Hilo(String nombre, MsLunch msLunch) {
        this.nombre = nombre;
        this.msLunch = msLunch;
    }
    public void run() {
        for (int i = 1; i <= 1000000; i++) {
            msLunch.inc1();
            msLunch.inc2();
        }
        System.out.println(nombre + " termina");
    }
}
