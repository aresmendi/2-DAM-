public class EjercicioHilos1 {
    public static class HiloNumerosLetras implements Runnable {

        private int tipo;

        public HiloNumerosLetras(int tipo) {
            this.tipo = tipo;
        }

        @Override
        public void run() {
            while (true) {
                switch (tipo) {
                    case 1 -> {
                        for (int i = 0; i < 30; i++) {
                            System.out.print(i);
                        }
                    }
                    case 2 -> {
                        for (char c = 'a'; c <= 'z'; c++) {
                            System.out.print(c);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        HiloNumerosLetras h1 = new HiloNumerosLetras(1);
        HiloNumerosLetras h2 = new HiloNumerosLetras(2);

        Thread t1 = new Thread(h1);
        Thread t2 = new Thread(h2);

        t1.start();
        t2.start();
    }
}
