class Atleta extends Thread {
    private static final Object testigo = new Object(); // Objeto compartido como testigo
    private int numeroAtleta;
    private long tiempoInicio;
    private long tiempoFinal;
    private Atleta siguienteAtleta;

    public Atleta(int numero) {
        this.numeroAtleta = numero;
    }

    public void setSiguienteAtleta(Atleta siguiente) {
        this.siguienteAtleta = siguiente;
    }

    @Override
    public void run() {
        synchronized (testigo) {
            try {
                // Esperar a que sea el turno de este atleta
                System.out.println("Atleta " + numeroAtleta + " esperando el testigo.");
                testigo.wait();
                
                // Simulación del tiempo de carrera
                tiempoInicio = System.currentTimeMillis();
                int tiempoCarrera = (int) (9000 + Math.random() * 2000); // Entre 9 y 11 segundos
                System.out.println("Atleta " + numeroAtleta + " está corriendo...");
                Thread.sleep(tiempoCarrera);
                
                tiempoFinal = System.currentTimeMillis();
                System.out.println("Atleta " + numeroAtleta + " terminó en " + (tiempoFinal - tiempoInicio) / 1000.0 + " segundos.");
                
                // Pasar el testigo al siguiente atleta
                if (siguienteAtleta != null) {
                    synchronized (siguienteAtleta) {
                        siguienteAtleta.notify(); // Despertar al siguiente atleta
                    }
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class Carrera {
    public static void main(String[] args) throws InterruptedException {
        // Crear atletas
        Atleta atleta1 = new Atleta(1);
        Atleta atleta2 = new Atleta(2);
        Atleta atleta3 = new Atleta(3);
        Atleta atleta4 = new Atleta(4);

        // Asignar siguiente atleta para pasar el testigo
        atleta1.setSiguienteAtleta(atleta2);
        atleta2.setSiguienteAtleta(atleta3);
        atleta3.setSiguienteAtleta(atleta4);

        // Iniciar los hilos
        atleta1.start();
        atleta2.start();
        atleta3.start();
        atleta4.start();

        // Iniciar la carrera pasando el testigo al primer atleta
        synchronized (atleta1) {
            atleta1.notify(); // Despertar al primer atleta
        }

        // Esperar a que todos los atletas terminen
        atleta1.join();
        atleta2.join();
        atleta3.join();
        atleta4.join();

        System.out.println("La carrera ha terminado.");
    }
}
