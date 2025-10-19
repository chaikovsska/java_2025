package main;

import java.util.List;

public class Road {
    final List<Vehicle<?>> carsInRoad;

    public Road(List<Vehicle<?>> carsInRoad) {
        this.carsInRoad = carsInRoad;
    }

    public int getCountOfHumans() {
        return carsInRoad.stream().mapToInt(Vehicle::getCurrentPassengerCount).sum();
    }

    public void addCarToRoad(Vehicle<?> vehicle) {
        carsInRoad.add(vehicle);
    }
}