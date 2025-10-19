package test;

import main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RoadTest {

    private Road road;
    private Bus bus;
    private Taxi taxi;
    private FireTruck fireTruck;
    private PoliceCar policeCar;

    @BeforeEach
    public void setUp() {
        bus = new Bus(50);
        taxi = new Taxi(4);
        fireTruck = new FireTruck(2);
        policeCar = new PoliceCar(2);

        List<Vehicle<?>> vehicles = new ArrayList<>();
        vehicles.add(bus);
        vehicles.add(taxi);
        vehicles.add(fireTruck);
        vehicles.add(policeCar);
        road = new Road(vehicles);
    }

    @Test
    public void testGetCountOfHumansInitially() {
        assertEquals(0, road.getCountOfHumans());
    }

    @Test
    public void testGetCountOfHumansAfterBoarding() throws Exception {
        bus.boardPassenger(new Human("Bus Passenger 1"));
        taxi.boardPassenger(new Human("Taxi Passenger 1"));
        fireTruck.boardPassenger(new Firefighter("Firefighter 1"));
        policeCar.boardPassenger(new Policeman("Police Officer 1"));

        assertEquals(4, road.getCountOfHumans());

        policeCar.boardPassenger(new Policeman("Police Officer 2"));
        assertEquals(5, road.getCountOfHumans());
    }

    @Test
    public void testAddCarToRoad() {
        assertEquals(0, road.getCountOfHumans());

        Taxi newTaxi = new Taxi(2);
        road.addCarToRoad(newTaxi);

        assertEquals(0, road.getCountOfHumans());

        try {
            newTaxi.boardPassenger(new Human("New Taxi Passenger"));
            assertEquals(1, road.getCountOfHumans());
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }
}