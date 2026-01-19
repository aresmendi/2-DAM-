public class Durmiente extends Thread{
    public void run(){
        while(true){
            try{
                System.out.println("Me duermo");
                Thread.sleep(1000);
                System.out.println("Me despierto");
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
}
