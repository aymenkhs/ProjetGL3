public class Usager {

    private int etageCourrant;
    private int destination;
    private String direction;

    public Usager(int etageCourrant, String direction) {
        this.etageCourrant = etageCourrant;

        this.direction = direction;
    }

    public int getEtageCourrant() {
        return etageCourrant;
    }

    public int getDestination() {
        return destination;
    }

    public String getDirection() {
        return direction;
    }

    public void prcessus_dappel(){
        /*
            the user call the elevator with the direction and the current stage, so technically we're going to call the
            constructor here (we may not use this function but we'll see)
        */
    }

    public void signaler_destination( int destination){
        // here we're going to let the user enter the destination he's going to
        this.destination = destination;
    }

    public void sortir(){} // we really don't need this function

}
