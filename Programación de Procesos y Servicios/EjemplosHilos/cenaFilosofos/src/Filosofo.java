import java.util.Random;

public class Filosofo implements Runnable{
    private  static Random aleatorio = new Random();
    private final Tenedor izquierdo, derecho;
    String  nombre;
    public Filosofo(String nombre, Tenedor izquierdo, Tenedor derecho) {
        this.nombre = nombre;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }
    @Override
    public void run() {
        while(true){
            pensar();
            comer();
        }
    }

    private void pensar(){
        System.out.println(nombre +" está pensando...");
        esperar(aleatorio.nextInt(2000,3000));
    }

    private void comer(){
        System.out.println(nombre + " tiene hambre y agarra el tenedor izquierdo");
        esperar(aleatorio.nextInt(500,800));
        synchronized (izquierdo){
            System.out.println(nombre + " agarra el tenedor derecho");
            esperar(aleatorio.nextInt(500,800));
            synchronized (derecho){
                System.out.println(nombre + " está comiendo");
                esperar(aleatorio.nextInt(2000,3000));
            }
            System.out.println(nombre + " ha soltado el tenedor derecho");
        }
        System.out.println(nombre + " ha soltado el tenedor izquierdo");
    }

    private void esperar(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println(nombre + " se asusta");
        }
    }

    public static void main(String[] args) {
        String[] nombres = {"Aristóteles", "Platón", "Sócrates", "Epicuro"};
        Tenedor[] tenedores = new Tenedor[nombres.length];
        for (int i = 0; i < tenedores.length; i++) {
            tenedores[i] = new Tenedor();
        }
        Filosofo[] filosofos = new Filosofo[nombres.length];
        for (int i = 0; i < filosofos.length; i++) {
            Tenedor izquierdo = tenedores[i% tenedores.length];
            Tenedor derecho = tenedores[(i+1)% tenedores.length];
            filosofos[i] = new Filosofo(nombres[i], izquierdo, derecho);
        }
        for (Filosofo filosofo : filosofos) {
            new Thread(filosofo).start();
        }
    }
}
