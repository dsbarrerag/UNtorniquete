package generator;

import java.util.Observable;

public abstract class Generator extends Observable implements Runnable {

    public boolean active = true;

    abstract long nextTime();

    @Override
    public void run() {
        while(active) {
            try {
                Thread.sleep(nextTime());
                setChanged();
                notifyObservers();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



