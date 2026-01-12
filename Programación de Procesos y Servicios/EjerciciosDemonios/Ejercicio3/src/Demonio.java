public class Demonio extends Thread {
    private Lista lista;

    public Demonio(Lista lista) {
        this.lista = lista;
    }

    @Override
    public void run() {
        while (true) {
            lista.limpiarMayoresDe(1000);
        }

    }
}
