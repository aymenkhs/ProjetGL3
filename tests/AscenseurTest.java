import enums.Direction;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AscenseurTest {

    @Test
    public void testdeplacer() {

        ArrayList<Porte> doorList= new ArrayList<>();
        for(int i=0;i<10;i++){
            Porte porte= new Porte(i);
            doorList.add(porte);
        }

        Ascenseur instance= new Ascenseur(10,doorList);

        instance.ajouterAppel(new Usager(2,Direction.Up,5));
        instance.ajouterAppel(new Usager(1,Direction.Up,3));
        instance.ajouterAppel(new Usager(6,Direction.Down,4));
        instance.ajouterAppel(new Usager(4,Direction.Down,1));
        instance.ajouterAppel(new Usager(2,Direction.Up,9));

        while(instance.getList_appels().size()!=0 || instance.getList_destinations().size()!=0){
            instance.deplacer();
        }

    }

    @Test
    public void updateDirection() {

        System.out.println("test : update direction");

        ArrayList<Porte> doorList= new ArrayList<>();
        for(int i=0;i<10;i++){
            Porte porte= new Porte(i);
            doorList.add(porte);
        }
        Ascenseur instance = new Ascenseur(10,doorList);

        instance.updateDirection();
    }

    @Test
    public void moving_from() {

        System.out.println("test : moving_from");

        ArrayList<Porte> doorList= new ArrayList<>();
        for(int i=0;i<10;i++){
            Porte porte= new Porte(i);
            doorList.add(porte);
        }
        Ascenseur instance = new Ascenseur(10,doorList);

        instance.moving_from();
    }

    @Test
    public void moving_to() {

        System.out.println("test : moving_to");

        ArrayList<Porte> doorList= new ArrayList<>();
        for(int i=0;i<10;i++){
            Porte porte= new Porte(i);
            doorList.add(porte);
        }
        Ascenseur instance = new Ascenseur(10,doorList);

        instance.moving_to();
    }
}