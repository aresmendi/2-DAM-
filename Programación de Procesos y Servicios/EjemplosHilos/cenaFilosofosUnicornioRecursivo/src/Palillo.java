public class Palillo {
    private boolean enUso;
    private int id;

    public Palillo(int id) {
        this.enUso = false;
        this.id = id;
    }

    public boolean isEnUso() {
        return enUso;
    }

    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Métodos propios
    public void usar(){
        enUso = true;
    }
    public void soltar(){
        enUso = false;
    }
}
