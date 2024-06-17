package skhemii.labs.lab6.chronometer;

public class Chronometer implements Runnable {

    private final int interval;
    private int count = 0;

    public Chronometer(int interval) {
        this.interval = interval;
    }

    public int getInterval() { return interval; }
    public int getCount() { return count; }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(interval * 1000L);
                count++;
            }
        } catch (InterruptedException ex) {
            System.out.println("Threading error!");
            ex.printStackTrace();
        }
    }
}
