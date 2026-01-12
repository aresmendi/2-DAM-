import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Demonio extends Thread {
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            } catch (InterruptedException e) {
            }
        }
    }
}
