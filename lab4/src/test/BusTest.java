package test;

import main.Bus;
import main.Human;
import main.Firefighter;
import main.Policeman;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusTest {

    private Bus bus;

    @BeforeEach
    public void setUp() {
        bus = new Bus(50);
    }

    @Test
    public void testBoardPassenger() throws Exception {
        bus.boardPassenger(new Human("Passenger 1"));
        assertEquals(1, bus.getCurrentPassengerCount());
    }

    @Test
    public void testBoardFirefighter() throws Exception {
        Firefighter firefighter = new Firefighter("Firefighter 1");
        bus.boardPassenger(firefighter);
        assertEquals(1, bus.getCurrentPassengerCount());
    }

    @Test
    public void testBoardPoliceman() throws Exception {
        Policeman policeman = new Policeman("Policeman 1");
        bus.boardPassenger(policeman);
        assertEquals(1, bus.getCurrentPassengerCount());
    }

    @Test
    public void testDisembarkPassenger() throws Exception {
        Human passenger = new Human("Passenger 1");
        bus.boardPassenger(passenger);
        bus.disembarkPassenger(passenger);
        assertEquals(0, bus.getCurrentPassengerCount());
    }

    @Test
    public void testExceedCapacity() throws Exception {
        for (int i = 1; i <= 50; i++) {
            bus.boardPassenger(new Human("Passenger " + i));
        }
        assertEquals(50, bus.getCurrentPassengerCount());

        Exception exception = assertThrows(Exception.class, () ->
                bus.boardPassenger(new Human("Extra Passenger")));
        assertEquals("No seats available", exception.getMessage());
    }

    @Test
    public void testDisembarkNonExistentPassenger() {
        Exception exception = assertThrows(Exception.class, () ->
                bus.disembarkPassenger(new Human("Nonexistent Passenger")));
        assertEquals("Nonexistent Passenger is not aboard this vehicle", exception.getMessage());
    }
}