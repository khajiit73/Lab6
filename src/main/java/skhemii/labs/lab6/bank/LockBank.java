package skhemii.labs.lab6.bank;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBank extends Bank {
    private final double[] accounts;
    private final Lock bankLock;
    private final Condition sufficientFunds;

    public LockBank(int accountsNum, int accountBalance) {
        accounts = new double[accountsNum];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = accountBalance;
        }

        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) {
        bankLock.lock();
        try {
            // if insufficient funds - wait until they become sufficient
            while (accounts[from] < amount) {
                sufficientFunds.await();
            }

            accounts[from] -= amount;
            System.out.printf(
                    "%s makes transfer of %6.2f$ from account %d to account %d\n",
                    Thread.currentThread(), amount, from, to);
            accounts[to] += amount;
            System.out.printf(
                    "Total Balance: %10.2f$\n", getTotalBalance());

            sufficientFunds.signalAll();
        } catch (InterruptedException ex) {
            System.out.println("Threading error!");
            ex.printStackTrace();
        } finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance() {
        bankLock.lock();
        try {
            double total = 0;
            for (int i = 0; i < accounts.length; i++) {
                total += accounts[i];
            }
            return total;
        } finally {
            bankLock.unlock();
        }
    }

    public int getSize() {
        return accounts.length;
    }
}
