package model;

import org.apache.commons.math3.distribution.ExponentialDistribution;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

public class Turnstile extends Observable implements Runnable {
    private int id;
    private ExponentialDistribution generator;
    private boolean entry;
    private AtomicBoolean busy, enabled;

    public Turnstile(int id, double mean, boolean entry){
        this.id = id;
        this.entry = entry;
        generator = new ExponentialDistribution(mean);
        busy = new AtomicBoolean(false);
        enabled = new AtomicBoolean(true);
    }

    @Override
    public void run() {
        busy.set(true);
        try {
            log();
            Thread.sleep((long) generator.sample()*1000);
            setChanged();
            notifyObservers();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        busy.set(false);
    }

    private void log() {
        System.out.println("Usuario en el torniquete: " + id);
    }

    public synchronized boolean isBusy() {
        return busy.get();
    }

    public boolean isEntry(){
        return entry;
    }

    public void setEntry(boolean entry){
        this.entry = entry;
    }

    public int getId() {
        return id;
    }

    public synchronized boolean isEnabled() {
        return enabled.get();
    }

    public synchronized void setEnabled(boolean disable) {
        this.enabled.set(disable);
    }
}
