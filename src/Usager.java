import enums.Direction;

public class Usager {

    private static int nb_usager = 0;

    private int etageCourrant;
    private int destination;
    private Direction direction;

    private int num_usager;

    public Usager(int etageCourrant, Direction direction, int destination) {
        this.etageCourrant = etageCourrant;
        this.direction = direction;
        this.num_usager = Usager.nb_usager;
        this.destination = destination;
        Usager.nb_usager++;
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

    public void entrer(){
        System.out.println("USAGER N°" + this.num_usager + ": Monte dans l'ascenceur");
    }

    public void signaler_destination(int destination){
        // here we're going to let the user enter the destination he's going to
        System.out.println("USAGER N°" + this.num_usager + ": entre la destination :" + this.destination);
    }

    public void sortir(){
        System.out.println("USAGER N°" + this.num_usager + ": Descent de l'ascenceur");
    } // well we need it



}
