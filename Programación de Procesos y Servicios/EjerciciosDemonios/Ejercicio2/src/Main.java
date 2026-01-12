public class Main {
    public static void main(String[] args) {
        BarraCarga barraCarga = new BarraCarga();
        barraCarga.setDaemon(true);
        System.out.println("Cargando información...");
        barraCarga.start();
        long i = 0;
        while(i< 20000000){
            i++;
        }
        System.out.println("Información cargada");
    }
}
