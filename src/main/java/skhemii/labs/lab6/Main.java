package skhemii.labs.lab6;

import skhemii.labs.lab6.bank.*;

public class Main {
    public static void main(String[] args) {

        BankTest();
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
}