public class MsLunch {
    private long c1;
    private long c2;
    //ESTO DE LOS LOCKS ES POR UNA MOVIDA DE RENDIMIENTO, PODRÍA SINCRONIZARSE LOS MÉTODOS Y FUNCIONARÍA IGUAL, PERO MÁS LENTO
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void inc1() {
        synchronized (lock1) {
            c1++;
        }
    }

    public void inc2() {
        synchronized (lock2) {
            c2++;
        }
    }
    public long getC1() {
        return c1;
    }

    public long getC2() {
        return c2;
    }
}
