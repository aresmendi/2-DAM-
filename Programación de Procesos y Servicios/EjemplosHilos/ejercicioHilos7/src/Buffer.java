public class Buffer {
    private char[] buffer;
    private int siguiente;
    private boolean estaVacia;
    private boolean estaLlena;

    public Buffer(int tam) {
        this.buffer = new char[tam];
        this.siguiente = 0;
        this.estaVacia = true;
        this.estaLlena = false;
    }

    public synchronized char consumir() {
        while (this.estaVacia) {
            try {
                this.wait();
            } catch (InterruptedException e) {}
        }
        siguiente--;
        this.estaLlena = false;
        if(siguiente == 0){
            this.estaVacia = true;
        }
        notifyAll();
        return buffer[siguiente];
    }

    public synchronized char producir(char c) {
        while(this.estaLlena){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        buffer[siguiente] = c;
        siguiente++;
        this.estaVacia = false;
        if(siguiente == buffer.length){
            this.estaLlena = true;
        }
        notifyAll();
        return buffer[siguiente];
    }
}
