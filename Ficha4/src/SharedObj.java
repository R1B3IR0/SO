public class SharedObj {
    private int number;
    private String name;

    // Métodos synchronized para obter e definir o número e o nomes
    public synchronized int getNumber() {
        return number;
    }
    public synchronized void setNumber(int number) {
        this.number = number;
    }
    public synchronized String getName() {
        return name;
    }
    public synchronized void setName(String name) {
        this.name = name;
    }
}
