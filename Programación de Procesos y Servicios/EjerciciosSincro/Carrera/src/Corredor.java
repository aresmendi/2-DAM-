import java.util.Random;

public class Corredor implements Runnable {
    private String nombre;
    private static  boolean ganador = false;
    public Corredor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println(nombre+ " sale disparado!");
        for(int i = 10;i<=100;i+=10){
            try {
                Thread.sleep(new Random().nextInt(500,1500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(nombre + " avanza! " + i+"/100");
        }
        System.out.println(nombre + " ha acabado la carrera!");

        synchronized (this) {
            if(!ganador){
                ganador = true;
                System.out.println(nombre + " ha ganado la carrera!");
            }
        }
    }
}
