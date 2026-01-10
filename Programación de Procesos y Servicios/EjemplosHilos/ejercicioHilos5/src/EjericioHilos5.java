public class EjericioHilos5 {
    public static void main(String[] args) {

        int[] numeros =new int[1000];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = i;
        }
        MediaHilo mh1 = new MediaHilo(numeros,0,250);
        MediaHilo mh2 = new MediaHilo(numeros,100,550);
        MediaHilo mh3 = new MediaHilo(numeros,200,450);
        MediaHilo mh4 = new MediaHilo(numeros,700,1000);

        mh1.start();
        mh2.start();
        mh3.start();
        mh4.start();
        //Tenemos que esperar a que todos acaben
        try {
            mh1.join();
            mh2.join();
            mh3.join();
            mh4.join();

            double r1 = mh1.getResultado();
            double r2 = mh2.getResultado();
            double r3 = mh3.getResultado();
            double r4 = mh4.getResultado();

            double media = (r1+r2+r3+r4) / 4;

            System.out.println("Media: "+media);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
