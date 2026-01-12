public class BarraCarga extends Thread{
    void carga(){
        System.out.println("|\n/\n—\n\\");
    }
    @Override
    public void run() {
        while(true){
            carga();
        }
    }
}
