public class Hilo extends Thread {
    int[] contador = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public void run() {
        for (int j : contador) {
            try {
                System.out.println(j);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
