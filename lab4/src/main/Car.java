package main;

public abstract class Car<T extends Human> extends Vehicle<T> {
    public Car(int capacity) {
        super(capacity);
    }
}