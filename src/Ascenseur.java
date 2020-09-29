import enums.Direction;

import java.util.ArrayList;
import java.util.Iterator;

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

    public void setDirection(Direction direction) { this.direction = direction; }

    public void setEtageCourrant(int etageCourrant) { this.etageCourrant = etageCourrant; }

    public ArrayList<Usager> getList_destinations() { return list_destinations; }

    public ArrayList<Usager> getList_appels() { return list_appels; }

    public Direction getDirection() { return direction; }

    public void ajouterAppel(Usager u){
        list_appels.add(u);
    }


    public void updateDirection(){
        /*
            on mets a jour la direction de l'ascenseur apres avoir ajouter un nouveau usager a la liste d'appel ou de
                destination ou apres être arriver à la destination d'un des usager dans la liste de destination
                                        d'apres les regles ennoncer dans le TP
        */

        // la liste de destination passe en priories
        if (!this.list_destinations.isEmpty()){
            if (this.list_destinations.get(0).getDestination() > this.etageCourrant){
                this.direction = Direction.Up;
            }else{
                this.direction = Direction.Down;
            }
        }else if (!this.list_appels.isEmpty()){
            // si la liste de destination est vide on verifie la liste d'appels, si elle n'est pas vide:
            if (direction == Direction.Up || direction == Direction.None){
                // si la direction de l'ascenseur est vers le haut ou none on verifie s'il y as des appel vers le haut
                if (check_call_up()) {
                    // si oui on mets la direction vers le haut
                    this.direction = Direction.Up;
                }else{
                    // sinon on verifie s'il y as des appel vers le bas
                    if (check_call_down()){
                        // si oui on mets la direction vers le bas
                        this.direction = Direction.Down;
                    }else{
                        // sinon on mets la direction a none
                        this.direction = Direction.None;
                    }
                }
            }else{
                // si la direction de l'ascenseur est vers le bas on verifie s'il y as des appel vers le bas
                if (check_call_down()){
                    // si oui on mets la direction vers le bas
                    this.direction = Direction.Down;
                }else{
                    // sinon on verifie s'il y as des appel vers le haut
                    if (check_call_up()){
                        // si oui on mets la direction vers le haut
                        this.direction = Direction.Up;
                    }else{
                        // sinon on mets la direction a none
                        this.direction = Direction.None;
                    }
                }
            }
        }else{
            // si la liste de destination et d'appel sont vide on met la direction a none
            this.direction = Direction.None;
        }

        if (direction == Direction.Up){
            System.out.println("ASCENSEUR: direction vers le haut");
        }else if (direction == Direction.Down){
            System.out.println("ASCENSEUR: direction vers le bas");
        }else{
            System.out.println("ASCENSEUR: reste a l'etage courrant avec direction none");
        }
    }

    private boolean check_call_up(){
        // fonction qui verifie s'il existe des appels vers le haut
        for (Usager u : this.list_appels){
            if (u.getEtageCourrant() > this.etageCourrant){
                return true;
            }
        }
        return false;
    }

    private boolean check_call_down(){
        // fonction qui verifie s'il existe des appels vers le bas
        for (Usager u : this.list_appels){
            if (u.getEtageCourrant() < this.etageCourrant){
                return true;
            }
        }
        return false;
    }

    public void deplacer(){
        /*
            cette fonction deplace l'assenceur d'un etage dans la direction courrante de l'assenceur puis verifie
            s'il y as des usager qui vont sortir de l'assenceur ou y entrer.
        */
        if (this.direction == Direction.None){
            // si on appel cette fonction et que la direction est None on verifie s'il y as des usager qui vont
            // sortir de l'assenceur ou y entrer puis on mets a jous la direction sans se deplacer.
            if (list_appels.isEmpty() && list_destinations.isEmpty()){
                // si il n'y a aucun usager, on ne fait rien
                return;
            }
            this.check_etage();
            this.updateDirection();
            return;
        }
        // on se deplace d'un etage
        if (direction.equals(Direction.Up)){
            etageCourrant ++;
        } else if (direction.equals(Direction.Down)){
            etageCourrant --;
        }
        System.out.println("+ Ascenseur arriver à l'etage n°" + etageCourrant);

        // on verifie s'il y as des usager a cet etage
        this.check_etage();

        // on mets a jour la direction en fonction des usager qui y sont sortie ou enter
        this.updateDirection();
    }

    private void check_etage(){
        // on verifie s'il y as des usager qui vont sortir de l'ascenseur, si oui tous les usager .
        this.moving_from();

        // on verifie s'il y as des usager qui vont entrer dans l'ascenseur, si oui on verifie qu'il vont dans la direction de l'ascenseur ,si oui ils entrent.
        this.moving_to();

        if (!this.list_portes.get(this.etageCourrant).isTheDoorClosed()){
            // si la porte a etait ouverte on la ferme
            System.out.println("L'ascensseur redemare");
            this.list_portes.get(this.etageCourrant).closeTheDoor();
        }
    }

    public void moving_from(){
        // on parcourt les usager dans l'ascenseur (la liste de destination), a chaque fois qu'on trouve un usager
        // dont la destination est l'etage courrant il sort et on le supprime de la liste de destinaion
        Iterator<Usager> iterator=list_destinations.iterator();
        while(iterator.hasNext()){
            Usager usager= iterator.next();
            if (usager.getDestination() == this.etageCourrant){
                if (this.list_portes.get(this.etageCourrant).isTheDoorClosed()){
                    // si la porte n'a pas encore etait ouverte on l'ouvre
                    System.out.println("L'ascensseur s'arrete");
                    this.list_portes.get(this.etageCourrant).openTheDoor();
                }
                usager.sortir();
                iterator.remove(); // on enleve l'usager de la liste des destination
            }
        }
    }

    public void moving_to(){
        // on parcourt la liste d'appel des usager, a chaque fois qu'on trouve un usager à l'etage courrant on verifie
        // si sa direction match avec celle de l'ascenseur si oui il entre et rentre sa destination
        Iterator<Usager> iterator=list_appels.iterator();
        while(iterator.hasNext()){
            Usager usager= iterator.next();

            if (usager.getEtageCourrant() == this.etageCourrant && this.direction == Direction.None){
                if (this.list_portes.get(this.etageCourrant).isTheDoorClosed()){
                    // si la porte n'a pas encore etait ouverte on l'ouvre
                    System.out.println("L'ascensseur s'arrete");
                    this.list_portes.get(this.etageCourrant).openTheDoor();
                }
                // si la direction de l'ascenseur est none, sa implique que le premier usager a cet etage dans la liste
                // d'appel peut entrer, et on met a jour la direction de l'ascenseur sur la direction de l'usager
                usager.entrer();
                usager.signaler_destination();
                iterator.remove(); // on enleve l'usager de la liste d'appel
                this.list_destinations.add(usager); // on ajoute l'usager de la liste des destination
                this.direction = usager.getDirection();
            }else if (usager.getEtageCourrant() == this.etageCourrant && usager.getDirection() == this.direction){
                if (this.list_portes.get(this.etageCourrant).isTheDoorClosed()){
                    // si la porte n'a pas encore etait ouverte on l'ouvre
                    System.out.println("L'ascensseur s'arrete");
                    this.list_portes.get(this.etageCourrant).openTheDoor();
                }
                usager.entrer();
                usager.signaler_destination();
                iterator.remove(); // on enleve l'usager de la liste d'appel
                this.list_destinations.add(usager); // on ajoute l'usager de la liste des destination
            }
        }
    }
}
