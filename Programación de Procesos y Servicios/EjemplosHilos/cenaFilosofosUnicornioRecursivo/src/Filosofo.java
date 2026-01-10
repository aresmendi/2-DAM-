public class Filosofo implements Runnable{
    private int id;
    private Mesa mesa;

    public Filosofo(int id, Mesa mesa) {
        this.id = id;
        this.mesa = mesa;
    }


    @Override
    public void run() {
        try{
            while(true){
                pensar();
                mesa.tomarPalillo(id);
                comer();
                mesa.soltarPalillo(id);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
    private  void pensar() throws InterruptedException {
        System.out.println("Yo el filósofo " + id +" estoy pensando...");
        Thread.sleep((long) (Math.random()*10000));
    }
    private  void comer() throws InterruptedException {
        System.out.println("Yo el filósofo " + id +" estoy comiendo...");
        Thread.sleep((long) (Math.random()*10000));
    }
}
