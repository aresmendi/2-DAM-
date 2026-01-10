import java.util.function.Consumer;

public class EjercicioHilos7 {
    public static void main(String[] args) {
        Buffer b = new Buffer(10);
        Productor p = new Productor(b);
        Consumidor c = new Consumidor(b);
        p.start();
        c.start();
    }
}
