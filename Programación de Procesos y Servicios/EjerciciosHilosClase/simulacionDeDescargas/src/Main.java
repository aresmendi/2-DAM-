public class Main {
    public static void main(String[] args) {
        DescargaArchivo d1 = new DescargaArchivo("Las alucinantes aventuras de Pepa la Cerda");
        DescargaArchivo d2 = new DescargaArchivo("XXX la de Vin Diesel que si de verdad");
        DescargaArchivo d3 = new DescargaArchivo("Obelix reventando nazis, que eso siempre entra bien");

        d1.start();
        d2.start();
        d3.start();
    }
}
