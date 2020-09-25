import enums.Status;
import org.junit.Test;

import static enums.Status.Closed;
import static org.junit.Assert.*;

public class PorteTest {

    @Test
    public void getEtage() {

        System.out.println("Test : getEtage");

        Porte instance= new Porte(3);
        int expresult= 3;
        int result=instance.getEtage();
        assertEquals(expresult,result);
    }

    @Test
    public void isTheDoorClosed() {

        System.out.println("Test : isTheDoorClosed");

        Porte instance= new Porte(4);
        boolean expResult= true;
        instance.closeTheDoor();
        boolean res= instance.isTheDoorClosed();
        assertEquals(expResult,res);

        instance.openTheDoor();
        expResult=false;
        res= instance.isTheDoorClosed();
        assertEquals(expResult,res);
    }
}