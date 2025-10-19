package test;

import main.FireTruck;
import main.Firefighter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FireTruckTest {

    private FireTruck fireTruck;

    @BeforeEach
    public void setUp() {
        fireTruck = new FireTruck(2);
    }

    @Test
    public void testBoardFirefighter() throws Exception {
        Firefighter firefighter = new Firefighter("Firefighter 1");
        fireTruck.boardPassenger(firefighter);
        assertEquals(1, fireTruck.getCurrentPassengerCount());
    }

    @Test
    public void testDisembarkFirefighter() throws Exception {
        Firefighter firefighter = new Firefighter("Firefighter 1");
        fireTruck.boardPassenger(firefighter);
        fireTruck.disembarkPassenger(firefighter);
        assertEquals(0, fireTruck.getCurrentPassengerCount());
    }

    @Test
    public void testExceedCapacity() throws Exception {
        fireTruck.boardPassenger(new Firefighter("Firefighter 1"));
        fireTruck.boardPassenger(new Firefighter("Firefighter 2"));
        assertEquals(2, fireTruck.getCurrentPassengerCount());

        Exception exception = assertThrows(Exception.class, () ->
                fireTruck.boardPassenger(new Firefighter("Extra Firefighter")));
        assertEquals("No seats available", exception.getMessage());
    }

    @Test
    public void testDisembarkNonExistentFirefighter() {
        Exception exception = assertThrows(Exception.class, () ->
                fireTruck.disembarkPassenger(new Firefighter("Nonexistent Firefighter")));
        assertEquals("Nonexistent Firefighter is not aboard this vehicle", exception.getMessage());
    }
}