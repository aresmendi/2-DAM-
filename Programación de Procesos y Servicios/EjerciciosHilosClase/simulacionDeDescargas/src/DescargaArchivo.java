public class DescargaArchivo extends Thread {
    private final String nombre;
    private int porcentaje;

    public DescargaArchivo(String nombre) {
        this.nombre = nombre;
        this.porcentaje = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
                if (porcentaje >= 100) {
                    System.out.println(nombre + " Descarga finalizada");
                    break;
                }
                System.out.println("Descargando archivo " + nombre + ": " + porcentaje);
                porcentaje += (int) Math.round(Math.random() * 10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
