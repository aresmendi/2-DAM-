import java.util.Random;

public class Cliente implements Runnable{
    private String nombre;
    private int dinero;
    private final Cuenta cuenta;
    public Cliente(String nombre, Cuenta cuenta) {
        this.nombre = nombre;
        this.cuenta = cuenta;
        this.dinero = 20;
    }
    @Override
    public void run() {
        while(dinero>0){
            boolean retirar = Math.random() < 0.5;
            int importe = new Random().nextInt(0, dinero);
            System.out.println(nombre+ " intenta " + (retirar ? "retirar " : "ingresar ") + importe);
            try {
                if(retirar){
                    cuenta.retirar(importe);
                    dinero += importe;
                } else {
                    cuenta.depositar(importe);
                    dinero -= importe;
                }
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("Dinero de " + nombre + ": " + dinero + ". Saldo en la cuenta " + cuenta.getSaldo());
        }
    }

}
