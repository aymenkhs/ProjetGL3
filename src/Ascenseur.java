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
        this.direction = Direction.None;
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
            }else{
                this.direction = Direction.Down;
            }
        }else if (!this.list_appels.isEmpty()){

            if (direction == Direction.Up || direction == Direction.None){
                // check if there is a call up
                if (check_call_up()) {
                    this.direction = Direction.Up;
                }else{
                    // check if there is a call down
                    if (check_call_down()){
                        this.direction = Direction.Down;
                    }else{
                        this.direction = Direction.None;
                    }
                }
            }else{
                // check if there is a call down
                if (check_call_down()){
                    this.direction = Direction.Down;
                }else{
                    // check if there is a call up
                    if (check_call_up()){
                        this.direction = Direction.Up;
                    }else{
                        this.direction = Direction.None;
                    }
                }
            }
        }else{
            this.direction = Direction.None;
        }

        if (direction == Direction.Up){
            System.out.println("ASCENSEUR: direction vers le haut");
        }else if (direction == Direction.Down){
            System.out.println("ASCENSEUR: direction vers le bas");
        }
    }

    private boolean check_call_up(){
        for (Usager u : this.list_appels){
            if (u.getEtageCourrant() > this.etageCourrant){
                return true;
            }
        }
        return false;
    }

    private boolean check_call_down(){
        for (Usager u : this.list_appels){
            if (u.getEtageCourrant() < this.etageCourrant){
                return true;
            }
        }
        return false;
    }

    public void deplacer(){
        /*
            we move until we arrive at the destination
        */
        if (this.direction == Direction.None){
            if (list_appels.isEmpty() && list_destinations.isEmpty()){
                return;
            }
            this.check_etage();
            this.updateDirection();
            return;
        }

        if (direction.equals(Direction.Up)){
            etageCourrant ++;
        } else if (direction.equals(Direction.Down)){
            etageCourrant --;
        }
        System.out.println("+ Ascenseur arriver à l'etage n°" + etageCourrant);
        this.check_etage();
        this.updateDirection();
    }

    private void check_etage(){
        // check if the current "etage" has anyone moving from the elevator in it
        this.moving_from();
        // check if the current "etage" has anyone entering the elevator in it
        this.moving_to();
        // close the gates
        this.list_portes.get(this.etageCourrant).closeTheDoor();
    }

    private void moving_from(){
        for (Usager usager : this.list_destinations){
            if (usager.getDestination() == this.etageCourrant){
                if (this.list_portes.get(this.etageCourrant).isTheDoorClosed()){
                    // open the gates
                    this.list_portes.get(this.etageCourrant).openTheDoor();
                }
                usager.sortir();
                this.list_destinations.remove(usager);
            }
        }
    }

    private void moving_to(){
        for (Usager usager : this.list_appels){

            if (usager.getEtageCourrant() == this.etageCourrant && this.direction == Direction.None){
                if (this.list_portes.get(this.etageCourrant).isTheDoorClosed()){
                    // open the gates
                    this.list_portes.get(this.etageCourrant).openTheDoor();
                }
                usager.entrer();
                this.list_appels.remove(usager);
                this.list_destinations.add(usager);
                this.direction = usager.getDirection();
            }else if (usager.getEtageCourrant() == this.etageCourrant && usager.getDirection() == this.direction){
                if (this.list_portes.get(this.etageCourrant).isTheDoorClosed()){
                    // open the gates
                    this.list_portes.get(this.etageCourrant).openTheDoor();
                }
                usager.entrer();
                this.list_appels.remove(usager);
                this.list_destinations.add(usager);
            }
        }
    }
}
