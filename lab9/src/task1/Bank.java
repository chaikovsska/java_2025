package task1;

public class Bank {
    public void transfer(Account from, Account to, int amount) {
        if (from.getId() == to.getId()) {
            return;
        }

        Account firstLockAccount = from.getId() < to.getId() ? from : to;
        Account secondLockAccount = from.getId() < to.getId() ? to : from;

        firstLockAccount.getLock().lock();
        try {
            secondLockAccount.getLock().lock();
            try {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            } finally {
                secondLockAccount.getLock().unlock();
            }
        } finally {
            firstLockAccount.getLock().unlock();
        }
    }
}
