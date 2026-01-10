public class Mesa { //Buffer
    private Palillo[] palillos;

    public Mesa(int numeroPalillos) {
        this.palillos = new Palillo[numeroPalillos];
    }
    public void generarPalillos(){
        for(int i=0;i<palillos.length;i++){
            palillos[i] = new Palillo(i);
        }
    }
    //Utilizamos el módulo para hacer la vuelta de reloj
    //Controlamos el error desde filósofo
    public synchronized void tomarPalillo(int idFilosofo) throws InterruptedException {
        Palillo derecha = palillos[idFilosofo];
        //5%5=0!!!
        Palillo izquierda  = palillos[(idFilosofo + 1)%palillos.length];
        while (derecha.isEnUso() || izquierda.isEnUso()){
            System.out.println("El filósofo " + idFilosofo + " está esperando para coger los palillos");
            wait();
        }
        System.out.println("Filosofo "+ idFilosofo + " toma el palillo " + derecha.getId() + " y " + izquierda.getId());
        derecha.usar();
        izquierda.usar();
    }
    public synchronized void soltarPalillo(int idFilosofo) throws InterruptedException {
        Palillo derecha = palillos[idFilosofo];
        //5%5=0!!!
        Palillo izquierda  = palillos[(idFilosofo + 1)%palillos.length];

        System.out.println("Filosofo "+ idFilosofo + " suelta el palillo " + derecha.getId() + " y " + izquierda.getId());
        derecha.soltar();
        izquierda.soltar();
        notify();
    }
}
