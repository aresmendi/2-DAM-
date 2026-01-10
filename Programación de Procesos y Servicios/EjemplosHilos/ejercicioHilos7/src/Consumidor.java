public class Consumidor extends Thread{
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }
    public void run() {
        while (true) {
            char c = this.buffer.consumir();
            System.out.println("Recogido el carácter "+ c + " del buffer");
            try {
                sleep((int) (Math.random() * 4000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
