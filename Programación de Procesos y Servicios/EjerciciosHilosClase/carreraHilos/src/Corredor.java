import java.util.Random;

public class Corredor implements Runnable {
    private final String nombre;
    private int distancia;

    public Corredor(String nombre) {
        this.nombre = nombre;
        this.distancia = 0;
    }

    @Override
    public void run() {
        System.out.println(nombre + " empieza la carrera!");
        for (int i = 0; i < 10; i++) {
            try {
                distancia += 10;
                if (distancia >= 100) {
                    distancia = 100;
                    System.out.println(nombre + " ha avanzado " + distancia);
                    System.out.println(nombre + " ha acabado la carrera!");
                    break;
                }
                System.out.println(nombre + " ha avanzado " + distancia);
                Thread.sleep(new Random().nextInt(500, 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getDistancia() {
        return distancia;
    }
}
