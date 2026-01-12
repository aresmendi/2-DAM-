public class Main {
    public static void main(String[] args) {
        Demonio demonio = new Demonio();
        demonio.setDaemon(true);
        demonio.start();
        for (int i = 0; i<10; i++){
            try{
                Thread.sleep(2000);
                System.out.println("Viendo pasar las horas...");
            }catch(InterruptedException e){}
        }
    }
}
