package test;

import main.PoliceCar;
import main.Policeman;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PoliceCarTest {

    private PoliceCar policeCar;

    @BeforeEach
    public void setUp() {
        policeCar = new PoliceCar(2);
    }

    @Test
    public void testBoardPoliceman() throws Exception {
        Policeman policeman = new Policeman("Policeman 1");
        policeCar.boardPassenger(policeman);
        assertEquals(1, policeCar.getCurrentPassengerCount());
    }

    @Test
    public void testDisembarkPoliceman() throws Exception {
        Policeman policeman = new Policeman("Policeman 1");
        policeCar.boardPassenger(policeman);
        policeCar.disembarkPassenger(policeman);
        assertEquals(0, policeCar.getCurrentPassengerCount());
    }

    @Test
    public void testExceedCapacity() throws Exception {
        policeCar.boardPassenger(new Policeman("Policeman 1"));
        policeCar.boardPassenger(new Policeman("Policeman 2"));
        assertEquals(2, policeCar.getCurrentPassengerCount());

        Exception exception = assertThrows(Exception.class, () ->
                policeCar.boardPassenger(new Policeman("Extra Policeman")));
        assertEquals("No seats available", exception.getMessage());
    }

    @Test
    public void testDisembarkNonExistentPoliceman() {
        Exception exception = assertThrows(Exception.class, () ->
                policeCar.disembarkPassenger(new Policeman("Nonexistent Policeman")));
        assertEquals("Nonexistent Policeman is not aboard this vehicle", exception.getMessage());
    }
}