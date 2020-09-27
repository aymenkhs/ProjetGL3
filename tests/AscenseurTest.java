import enums.Direction;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AscenseurTest {

    @Test
    public void testdeplacer() {
        System.out.println("Test : deplacer");

        //On teste si la fct deplacer deplace l'ascenseur et appelle aux fcts d'entrée et de sortie des usagers correctement
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

        Direction expResult = Direction.None;

        //les 2 listes sont vides
        instance.updateDirection();
        assertEquals(expResult,instance.getDirection());

        //on ajoute des usagers dans la liste d'appels
        instance.ajouterAppel(new Usager(1,Direction.Up,2));
        expResult= Direction.Up;
        instance.updateDirection();
        assertEquals(expResult,instance.getDirection());

        while(instance.getList_appels().size()!=0 || instance.getList_destinations().size()!=0){
            instance.deplacer();
        }

        instance.ajouterAppel(new Usager(1,Direction.Down,0));
        expResult=Direction.Down;
        instance.updateDirection();
        assertEquals(expResult,instance.getDirection());
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
        instance.ajouterAppel(new Usager(0,Direction.Up,2));
        instance.moving_to();
        //On teste si l'usager sort de l'ascenseur a sa destination (qui laissera la liste de destination vide
        instance.setEtageCourrant(2);
        int expres= 0;
        instance.moving_from();
        assertEquals(expres,instance.getList_destinations().size());
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
        Usager user=new Usager(0,Direction.Up,2);
        instance.ajouterAppel(user);

        //On teste si la fct supprimes l'usager de la liste des appels et l'ajoute dans la liste de destinations
        //Qui veut dire que l'usager monte dans l'ascenseur
        ArrayList<Usager> expResult= new ArrayList<>();
        expResult.add(user);
        instance.moving_to();
        assertEquals(expResult,instance.getList_destinations());
    }
}