import java.util.ArrayList;

public class Ascenseur {

    private int nb_etages;
    private ArrayList<Porte> list_portes;

    private int etageCourrant;
    private int destination;
    private String direction;
    private ArrayList<Usager> list_appels;

    public Ascenseur(int nb_etages) {
        this.nb_etages = nb_etages;
        this.list_portes = new ArrayList<Porte>();
        for(int i=0; i<=nb_etages;i++){
            this.list_portes.add(new Porte(i));
        }

        this.etageCourrant = 0;
        this.direction = "none";
        this.list_appels = new ArrayList<Usager>();
    }
}
