public class Autoguardado extends Thread{
    public void run(){
        while(true){
            try{
                Thread.sleep(2000);
                System.out.println("Documento guardado...");
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
