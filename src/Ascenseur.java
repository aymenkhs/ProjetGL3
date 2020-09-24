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

        if (!this.list_destinations.isEmpty()){
            if (this.list_destinations.get(0).getDestination() > this.etageCourrant){
                this.direction = Direction.Up;
            }else {
                this.direction = Direction.Down;
            }
        }else {
            if (!this.list_appels.isEmpty()){
                if (this.list_destinations.get(0).getEtageCourrant() > this.etageCourrant){
                    this.direction = Direction.Up;
                }else {
                    this.direction = Direction.Down;
                }
            }
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
        this.moving_from();
        // chezck if the current etage has anyone entering the elevator in it
        this.moving_to();
    }

    private void moving_from(){
        for (Usager usager : this.list_destinations){
            if (usager.getDestination() == this.etageCourrant){
                // open the gates
                usager.sortir();
                // close the gates
                this.list_destinations.remove(usager);
            }
        }
    }

    private void moving_to(){
        for (Usager usager : this.list_appels){
            if (usager.getEtageCourrant() == this.etageCourrant){
                // open the gates
                usager.entrer();
                // close the gates
                this.list_appels.remove(usager);
                this.list_destinations.add(usager);
            }
        }
    }


}
