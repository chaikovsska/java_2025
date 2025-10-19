package test;

import main.Firefighter;
import main.Human;
import main.Policeman;
import main.Taxi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxiTest {

    private Taxi taxi;

    @BeforeEach
    public void setUp() {
        taxi = new Taxi(4);
    }

    @Test
    public void testBoardPassenger() throws Exception {
        taxi.boardPassenger(new Human("Taxi Passenger 1"));
        assertEquals(1, taxi.getCurrentPassengerCount());
    }

    @Test
    public void testBoardFirefighter() throws Exception {
        Firefighter firefighter = new Firefighter("Firefighter 1");
        taxi.boardPassenger(firefighter);
        assertEquals(1, taxi.getCurrentPassengerCount());
    }

    @Test
    public void testBoardPoliceman() throws Exception {
        Policeman policeman = new Policeman("Policeman 1");
        taxi.boardPassenger(policeman);
        assertEquals(1, taxi.getCurrentPassengerCount());
    }

    @Test
    public void testDisembarkPassenger() throws Exception {
        Human passenger = new Human("Taxi Passenger 1");
        taxi.boardPassenger(passenger);
        taxi.disembarkPassenger(passenger);
        assertEquals(0, taxi.getCurrentPassengerCount());
    }

    @Test
    public void testExceedCapacity() throws Exception {
        for (int i = 1; i <= 4; i++) {
            taxi.boardPassenger(new Human("Passenger " + i));
        }
        assertEquals(4, taxi.getCurrentPassengerCount());

        Exception exception = assertThrows(Exception.class, () ->
                taxi.boardPassenger(new Human("Extra Passenger")));
        assertEquals("No seats available", exception.getMessage());
    }

    @Test
    public void testDisembarkNonExistentPassenger() {
        Exception exception = assertThrows(Exception.class, () ->
                taxi.disembarkPassenger(new Human("Nonexistent Passenger")));
        assertEquals("Nonexistent Passenger is not aboard this vehicle", exception.getMessage());
    }
}