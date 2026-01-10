public class PingPong extends Thread{
    private String word;
    public PingPong(String word){
        this.word = word;
    }
    public synchronized String setWord(String word){
        this.word = word;
        return word;
    }
    public void run() {
        for(int i = 0; i < 300; i++) {
            System.out.print(setWord(word));
            System.out.flush();
        }
    }

    public static void main(String[] args) {
        Thread tP = new PingPong("P");
        Thread tp = new PingPong("p");
        tp.setPriority(Thread.MIN_PRIORITY);
        tP.setPriority(Thread.MAX_PRIORITY);
        tp.start();
        tP.start();
    }
}
