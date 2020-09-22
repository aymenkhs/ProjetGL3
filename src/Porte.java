public class Porte {

    private int etage;
    private String status; // "closed" or "open"

    public Porte(int etage) {
        this.etage = etage;
        this.status = "closed";
    }

    public int getEtage() {
        return etage;
    }

    public boolean isTheDoorClosed() {
        return status.equals("closed");
    }

    public void closeTheDoor() {
        this.status = "closed";
    }

    public void openTheDoor() {
        this.status = "open";
    }
}
