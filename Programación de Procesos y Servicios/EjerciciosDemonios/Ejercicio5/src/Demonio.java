import java.util.Random;

public class Demonio extends Thread{
    public void run(){
        while(true){
            try{
                Thread.sleep(300);
                System.out.println(new Random().nextInt(100) + "%");
            }catch(InterruptedException e){}
        }
    }
}
