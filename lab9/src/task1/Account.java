package task1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    private final int id;
    private int balance;
    private final Lock lock = new ReentrantLock();

    public Account(int balance) {
        this.id = idGenerator.getAndIncrement();
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public Lock getLock() {
        return lock;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }
}