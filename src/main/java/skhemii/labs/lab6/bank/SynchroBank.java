package skhemii.labs.lab6.bank;

public class SynchroBank extends Bank {
    private final double[] accounts;

    public SynchroBank(int accountsNum, int accountBalance) {
        accounts = new double[accountsNum];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = accountBalance;
        }
    }

    public synchronized void transfer(int from, int to, double amount) {
        try {
            // if insufficient funds - wait until they become sufficient
            while (accounts[from] < amount) {
                wait();
            }

            accounts[from] -= amount;
            System.out.printf(
                    "%s makes transfer of %6.2f$ from account %d to account %d\n",
                    Thread.currentThread(), amount, from, to);
            accounts[to] += amount;
            notifyAll();
            System.out.printf(
                    "Total Balance: %10.2f$\n", getTotalBalance());
        } catch (InterruptedException ex) {
            System.out.println("Threading error!");
            ex.printStackTrace();
        }
    }

    public synchronized double getTotalBalance() {
        double total = 0;
        for (int i = 0; i < accounts.length; i++) {
            total += accounts[i];
        }
        return total;
    }

    public int getSize() {
        return accounts.length;
    }
}

