public class Main {
    public static void main(String[] args) {
        int numPalillos = 5;
        //Hay tantos palillos como filósofos
        Mesa mesa =  new Mesa(numPalillos);
        mesa.generarPalillos();
        for (int i = 0; i < numPalillos; i++) {
            Filosofo f = new Filosofo(i, mesa);
            Thread  t = new Thread(f);
            t.start();
        }//new Thread(newFilosofo(i,mesa)).start();
    }

}
