public class Contador extends Thread{
    public void run(){
        while(true){
            for (int i = 0; i<1000000; i++){
                System.out.print(i);
                if(isInterrupted()){
                    System.out.println("El hilo ha sido interrumpido");
                }
            }
        }
    }
}
