import enums.Status;

public class Porte {

    private int etage;
    private Status status; // "closed" ou "open"

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
        System.out.println("PORTE : Fermeture de la porte de l'etage" + this.etage);
        this.status = Status.Closed;
    }

    public void openTheDoor() {
        System.out.println("PORTE : Ouverture de la porte de l'etage" + this.etage);
        this.status = Status.Open;
    }
}
