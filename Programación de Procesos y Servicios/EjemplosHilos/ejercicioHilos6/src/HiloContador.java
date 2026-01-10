public class HiloContador extends Thread{
    private int id;
    private Counter counter;
    private int n;

    public HiloContador(int id, Counter counter, int n) {
        this.id = id;
        this.counter = counter;
        this.n = n;
    }
    @Override
    public void run() {
        if(counter.isSincronizado()){
            counter.mostrarNumerosSincronizado(id,n);
        } else {
            counter.mostrarNumerosNoSincronizado(id,n);
        }
    }
}
