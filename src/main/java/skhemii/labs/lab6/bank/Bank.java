package skhemii.labs.lab6.bank;

public abstract class Bank {
    public Bank() {}
    /**
     * Отримати кількість рахунків в банку
     * @return - к-ть рахунків в банку
     */
    public abstract int getSize();
    /**
     * Сукупний баланс грошей в банку
     * @return - загальний баланс
     */
    public abstract double getTotalBalance();
    /**
     * Переказ грошей з одного рахунку на інший
     * @param from - рахунок, з якого переказуватимуть гроші
     * @param to - рахунок, на який переказуватимуть гроші
     * @param amount - сума переказу
     */
    public abstract void transfer(int from, int to, double amount);
}