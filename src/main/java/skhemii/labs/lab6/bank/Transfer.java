package skhemii.labs.lab6.bank;

public class Transfer implements Runnable {
    private static final double DELAY = 10;
    private final Bank bank;
    private final int fromAccount;
    private final int maxAmount;
    /**
     * Конструктор
     * @param bank - банк, між рахунками якого виконується переказ
     * @param from - рахунок, з якого виконуватиметься переказ
     * @param maxAmount - максимальна сума одного переказу
     */
    public Transfer(Bank bank, int from, int maxAmount) {
        this.bank = bank;
        this.fromAccount = from;
        this.maxAmount = maxAmount;
    }
    /**
     * Реалізація інтерфейсу Runnable
     */
    public void run() {
        try {
            while (true) {
                int toAccount = (int) (Math.random() * bank.getSize());
                double amount = (double) (Math.random() * maxAmount);
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((long) (Math.random() * DELAY));
            }
        } catch (InterruptedException ex) {
            System.out.println("Помилка при роботіз потоками!");
            ex.printStackTrace();
        }
    }
}