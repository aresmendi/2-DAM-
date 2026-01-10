public class Main {
    public static void main(String[] args) {
        try {
            MiHilo miHilo1 = new MiHilo("Primer Hilo");
            MiHilo miHilo2 = new MiHilo("Segundo Hilo");
            System.out.println(miHilo1.getNombre() + " está " + miHilo1.getState());
            System.out.println(miHilo2.getNombre() + " está " + miHilo2.getState());
            miHilo1.start();
            //Damos tiempo para que lo pille dormido
            Thread.sleep(300);
            System.out.println(miHilo1.getNombre() + " está " + miHilo1.getState());
            miHilo2.start();
            //El otro lo veremos runnable
            System.out.println(miHilo2.getNombre() + " está " + miHilo2.getState());
            //Así espera el main a que los hijos terminen NO CON WAIT
            miHilo1.join();
            miHilo2.join();
            System.out.println(miHilo1.getNombre() + " está " + miHilo1.getState());
            System.out.println(miHilo2.getNombre() + " está " + miHilo2.getState());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
