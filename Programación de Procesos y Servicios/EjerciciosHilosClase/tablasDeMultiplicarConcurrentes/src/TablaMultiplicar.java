public class TablaMultiplicar implements Runnable{
    private final int num;

    public TablaMultiplicar(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(num + "*" + i + " = " + i * num);
        }
    }
}
