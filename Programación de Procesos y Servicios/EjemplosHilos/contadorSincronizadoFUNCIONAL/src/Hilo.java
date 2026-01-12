public class Hilo extends Thread {
    private Contador contador;
    private String nombre;

    public Hilo(String nombre, Contador contador) {
        this.nombre = nombre;
        this.contador = contador;
    }

    @Override
    public void run() {
        //MUCHAS ITERACIONES PARA PROVOCAR EL FALLO (NO DEBERÍA HABERLO AHORA)
        for (int i = 0; i < 100000000; i++) {
            contador.increment();
            contador.decrement();
        }
        System.out.println(nombre + " termina con c = " + contador.value());
    }
}
