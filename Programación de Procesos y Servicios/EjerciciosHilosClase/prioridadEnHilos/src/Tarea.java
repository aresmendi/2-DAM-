public class Tarea extends Thread{
    private final String nombre;

    public Tarea(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public void run() {
        for(int i = 0; i < 20; i++){
            System.out.println(this.nombre);
        }
    }
}
