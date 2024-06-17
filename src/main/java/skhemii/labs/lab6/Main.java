package skhemii.labs.lab6;

import skhemii.labs.lab6.bank.*;
import skhemii.labs.lab6.chronometer.TimeDisplay;

public class Main {
    public static void main(String[] args) {
        //BankTest();
        ChronometerTest();
    }

    private static void BankTest() {
        final int ACCOUNTS = 100;   // amount of accounts in the bank
        final int BALANCE = 1000;   // total amount on all accounts

        //Bank bank = new SimpleBank(ACCOUNTS, BALANCE);
        Bank bank = new LockBank(ACCOUNTS, BALANCE);
        //Bank bank = new SynchroBank(ACCOUNTS, BALANCE);

        for (int i = 0; i < ACCOUNTS; i++) {
            Transfer transfer = new Transfer(bank, i, BALANCE);
            Thread thread = new Thread(transfer);
            thread.start();
        }
    }
    private static void ChronometerTest() {
        TimeDisplay display = new TimeDisplay();
        display.addChronometer(1);
        display.addChronometer(5);
        display.addChronometer(10);
        display.addChronometer(15);
        display.addChronometer(20);
        display.run();
    }
}