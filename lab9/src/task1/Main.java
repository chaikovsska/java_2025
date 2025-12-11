package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        List<Account> accounts = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            accounts.add(new Account(random.nextInt(1000) + 100));
        }

        int initialBalance = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("Initial bank balance: " + initialBalance);

        List<Thread> threads = getThreads(random, accounts, bank);
        for (Thread thread : threads) {
            thread.join();
        }

        int finalBalance = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("Final bank balance: " + finalBalance);
        if (initialBalance == finalBalance) {
            System.out.println("Test passed! The total amount in the bank is consistent.");
        } else {
            System.out.println("Test failed! The total amount in the bank is inconsistent.");
        }
    }

    private static List<Thread> getThreads(Random random, List<Account> accounts, Bank bank) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {
                int fromIndex = random.nextInt(accounts.size());
                int toIndex = random.nextInt(accounts.size());
                while (fromIndex == toIndex) {
                    toIndex = random.nextInt(accounts.size());
                }

                Account from = accounts.get(fromIndex);
                Account to = accounts.get(toIndex);
                int currentBalance = from.getBalance();
                if (currentBalance > 0) {
                    int amount = random.nextInt(currentBalance + 1);
                    bank.transfer(from, to, amount);
                }
            });
            threads.add(thread);
            thread.start();
        }

        return threads;
    }
}
