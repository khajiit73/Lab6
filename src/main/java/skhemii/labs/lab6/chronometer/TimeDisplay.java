package skhemii.labs.lab6.chronometer;

import java.util.ArrayList;
import java.util.List;

public class TimeDisplay implements Runnable {
    private final List<Chronometer> chronometers;
    private int refreshInterval = 1;

    public TimeDisplay() {
        this.chronometers = new ArrayList<>();
    }

    public void addChronometer(int interval) {
        this.chronometers.add(new Chronometer(interval));
    }

    public void printChronometers() {
        System.out.printf("\r");
        for (Chronometer chronometer : chronometers) {
            System.out.printf("%ds: %d\t\t", chronometer.getInterval(), chronometer.getCount());
        }
    }

    @Override
    public void run() {
        for (Chronometer chronometer : chronometers) {
            new Thread(chronometer).start();
        }

        try {
            while (true) {
                Thread.sleep(refreshInterval * 1000L);
                printChronometers();
            }
        } catch (InterruptedException ex) {
            System.out.println("Threading error!");
            ex.printStackTrace();
        }
    }
}
