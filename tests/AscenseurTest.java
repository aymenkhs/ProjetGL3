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

        //les 2 listes sont vides, on verifie la direction
        instance.updateDirection();
        assertEquals(expResult,instance.getDirection());

        //on ajoute des usagers dans la liste d'appels,puis on verifie la direction
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

        //On mets l'ascenseur au milieu, Puis 2 usagers font 2 appels depuis 2 etages de coté different,
        //Puis on teste que la priorité est a l'usager qui est dans la direction de l'ascenseur

        Usager usager1= new Usager(3,Direction.Up,7);
        Usager usager2= new Usager(8,Direction.Down,2);

        //Dans ce cas, la direction de l'ascenseur est Down, donc malgré que la direction de l'usager1 est Up, il est priotirisé,
        instance.ajouterAppel(usager1);
        instance.ajouterAppel(usager2);
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

        //2 usager 1 etage direction opposé,
        instance.setEtageCourrant(5);
        instance.setDirection(Direction.Up);
        Usager user1= new Usager(5,Direction.Up,7);
        Usager user2= new Usager(5,Direction.Down,4);
        instance.ajouterAppel(user1);
        instance.ajouterAppel(user2);

        instance.moving_to();
        //On s'assure que seulement l'usager 1 est entrer dans l'ascenseur car ils ont la méme direction
        //Alors que l'usager 2 reste dans la liste d'appels
        assertTrue(instance.getList_destinations().contains(user1));
        assertTrue(!(instance.getList_appels().contains(user1)));

        assertFalse(instance.getList_destinations().contains(user2));
        assertFalse(!(instance.getList_appels().contains(user2)));


        //ascenseur ne s'arrete pas pour usager3 car ils n'ont pas la meme direction
        Usager user3= new Usager(6,Direction.Down,3);
        instance.ajouterAppel(user3);

        //on deplace l'ascenseur d'une etage et on verifie si user 3 est entrer ou non:
        instance.setEtageCourrant(6);
        instance.moving_to();//Rien ne se passe

        assertFalse(instance.getList_destinations().contains(user3));
        assertTrue(instance.getList_appels().contains(user3));
    }
}