public class Bucle extends Thread{
    public void run(){
        while(true){
            if(isInterrupted()){
                System.out.println("Bucle interrumpido");
                break;
            }
        }
    }
}
