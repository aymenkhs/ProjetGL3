import enums.Direction;

import java.util.ArrayList;

public class Ascenseur {

    private int nb_etages;
    private ArrayList<Porte> list_portes;

    private int etageCourrant;
    private Direction direction;
    private ArrayList<Usager> list_destinations;
    private ArrayList<Usager> list_appels;

    public Ascenseur(int nb_etages, ArrayList<Porte> list_portes) {
        this.nb_etages = nb_etages;
        this.list_portes = list_portes;

        this.etageCourrant = 0;
        this.direction = Direction.Up;
        this.list_appels = new ArrayList<Usager>();
        this.list_destinations = new ArrayList<Usager>();
    }

    public void ajouterAppel(Usager u){
        list_appels.add(u);
    }

    public void updateDirection(){
        /*
            we update the direction after adding a new call from the user or after arriving on the destination based on the rules of the TP
        */

        // here maybe we should put the direction to "none" cause there's no direction, or we should remove the direction entirely
        if (etageCourrant ==  nb_etages){
            direction = Direction.Down;
        }else if(etageCourrant ==  0){
            direction = Direction.Up;
        }
    }

    public void deplacer(){
        /*
            we move until we arrive at the destination
        */
        if (list_appels.isEmpty() && list_destinations.isEmpty()){
            return;
        }

        if (direction.equals(Direction.Up)){
            etageCourrant ++;
        } else if (direction.equals(Direction.Down)){
            etageCourrant --;
        }
        System.out.println("+ Ascenseur arriver à l'etage n°" + etageCourrant);
        // chezck if the current etage has anyone moving from the elevator in it
        // chezck if the current etage has anyone entering the elevator in it 
    }
}
