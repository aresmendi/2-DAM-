import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Lista {
    private final ArrayList<Integer> numeros = new ArrayList<>();

    public synchronized void agregar() {
        int i = new Random().nextInt(100, 6000);
        numeros.add(i);
    }

    public void eliminar(int i) {
        numeros.remove(i);
    }

    public ArrayList<Integer> getNumeros() {
        return numeros;
    }

    public synchronized void limpiarMayoresDe(int limite) {
        Iterator<Integer> it = numeros.iterator();
        while (it.hasNext()) {
            int numero = it.next();
            if (numero > limite) {
                System.out.println("Eliminando número "+numero);
                it.remove();
            }
        }
    }
}
