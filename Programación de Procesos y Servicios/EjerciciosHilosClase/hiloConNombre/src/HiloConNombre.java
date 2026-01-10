public class HiloConNombre implements Runnable {
    private String nombre;

    public HiloConNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(nombre);
                System.out.println("Mensajitooooooo de hilo " + nombre);

                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
