package main;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Human> {
    private final int maxSeats;
    private final List<T> passengers;

    public Vehicle(int maxSeats) {
        this.maxSeats = maxSeats;
        this.passengers = new ArrayList<>();
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getCurrentPassengerCount() {
        return passengers.size();
    }

    public void boardPassenger(T person) throws Exception {
        if (passengers.size() < maxSeats) {
            passengers.add(person);
        } else {
            throw new Exception("No seats available");
        }
    }

    public void disembarkPassenger(T person) throws Exception {
        if (passengers.contains(person)) {
            passengers.remove(person);
        } else {
            throw new Exception(person.getName() + " is not aboard this vehicle");
        }
    }
}