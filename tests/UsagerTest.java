import enums.Direction;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsagerTest {

    @Test
    public void getEtageCourrant() {

        System.out.println("Test : getEtageCourrant");

        Usager instance= new Usager(4,Direction.Up,6);
        int expresult=4;
        int res= instance.getEtageCourrant();
        assertEquals(expresult,res);
    }

    @Test
    public void getDestination() {

        System.out.println("Test : getDestination");

        Usager instance= new Usager(4,Direction.Up,6);
        int expresult=6;
        int res= instance.getDestination();
        assertEquals(expresult,res);
    }

    @Test
    public void getDirection() {

        System.out.println("Test : getDirection");

        Usager instance= new Usager(4,Direction.Up,6);
        Direction expresult=Direction.Up;
        Direction res= instance.getDirection();
        assertEquals(expresult,res);
    }

}