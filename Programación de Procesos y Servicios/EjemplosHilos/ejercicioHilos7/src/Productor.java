public class Productor extends Thread{
    private Buffer buffer;
    private final String letras = "abcdefghijklmnopqrstuvqxyz";

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }
    public void run() {
        while (true) {
            char c = letras.charAt((int) (Math.random() * letras.length()));
            buffer.producir(c);
            System.out.println("Depositado el caracter " + c + " del buffer");

            try {
                sleep((int) (Math.random() * 4000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
