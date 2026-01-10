public class MiHilo extends Thread {
    private int[] contador;
    private String nombre;
    public MiHilo(String nombre) {
        this.nombre = nombre;
        this.contador = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 8, 10};
    }
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i : contador) {
            try {
                System.out.println(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getNombre() {
        return nombre;
    }
}
