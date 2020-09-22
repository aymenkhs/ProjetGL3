import enums.Status;

public class Porte {

    private int etage;
    private Status status; // "closed" or "open"

    public Porte(int etage) {
        this.etage = etage;
        this.status = Status.Closed;
    }

    public int getEtage() {
        return etage;
    }

    public boolean isTheDoorClosed() {
        return status.equals(Status.Closed);
    }

    public void closeTheDoor() {
        this.status = Status.Closed;
    }

    public void openTheDoor() {
        this.status = Status.Open;
    }
}
