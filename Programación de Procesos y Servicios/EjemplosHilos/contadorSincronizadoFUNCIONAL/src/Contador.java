public class Contador {
    int c = 0;

    //SINCRONIZAMOS LOS MÉTODOS PARA QUE EL HILO ACCEDA AL BLOQUEO INTRÍNSECO DEL OBJETO CONTADOR
    synchronized void increment() {
        c++;
    }

    synchronized void decrement() {
        c--;
    }

    int value() {
        return c;
    }
}
