public class Contador extends Thread{
    public void run(){
        while(true){
            for(int i=0;i<=100;i++){
                System.out.println(i);
                if(isInterrupted()){
                    System.out.println("HILO INTERRUMPIDO");
                    break;
                }
                if(i%10==0){
                    Thread.yield();
                }
            }
        }
    }
}
