public class MediaHilo extends Thread{
    private int [] numeros;
    private int inicio;
    private int fin;
    private double resultado;

    public MediaHilo(int[] numeros, int inicio, int fin) {
        this.numeros = numeros;
        this.inicio = inicio;
        this.fin = fin;
        this.resultado = 0;
    }
    @Override
    public void run() {
        int suma = 0;
        for (int i=inicio;i<fin;i++){
            suma+=numeros[i];
        }
        this.resultado = (double) suma /(fin-inicio);
    }

    public double getResultado() {
        return resultado;
    }
}
