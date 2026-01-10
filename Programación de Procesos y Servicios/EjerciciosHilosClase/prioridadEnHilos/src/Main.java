public class Main {
    public static void main(String[] args) {
        Tarea t1 = new Tarea("Tarea 1");
        Tarea t2 = new Tarea("Tarea 2");
        Tarea t3 = new Tarea("Tarea 3");
        //Se la resuda lo de las prioridades muchísimo a Java
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }
}
