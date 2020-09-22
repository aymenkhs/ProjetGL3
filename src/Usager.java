import enums.Direction;

public class Usager {

    private int etageCourrant;
    private int destination;
    private Direction direction;

    public Usager(int etageCourrant, Direction direction) {
        this.etageCourrant = etageCourrant;

        this.direction = direction;
    }

    public int getEtageCourrant() {
        return etageCourrant;
    }

    public int getDestination() {
        return destination;
    }

    public Direction getDirection() {
        return direction;
    }

    public void prcessus_dappel(){
        /*
            the user call the elevator with the direction and the current stage, so technically we're going to call the
            constructor here (we may not use this function but we'll see)
        */
    }

    public void entrer(){}

    public void signaler_destination(int destination){
        // here we're going to let the user enter the destination he's going to
        this.destination = destination;
    }

    public void sortir(){} // well we need it



}
