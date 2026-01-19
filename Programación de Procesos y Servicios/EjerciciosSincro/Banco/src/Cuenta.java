public class Cuenta {
    private final String nombre;
    private int saldo;
    public Cuenta(String nombre)
    {
        this.nombre=nombre;
        this.saldo=0;
    }

    public synchronized int getSaldo() {
        return saldo;
    }

    public synchronized void retirar(int cantidad){
        if(cantidad<=0){
            throw new IllegalArgumentException("Cantidad inválida");
        }
        if(saldo < cantidad){
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        saldo -= cantidad;
    }
    public synchronized void depositar(int cantidad){
        if(cantidad<=0){
            throw new IllegalArgumentException("Cantidad inválida");
        }
        saldo += cantidad;
    }
}
