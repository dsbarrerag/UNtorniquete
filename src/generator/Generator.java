package generator;

import java.util.Observable;

public abstract class Generator extends Observable implements Runnable {

    abstract long nextTime();

    @Override
    public void run() {
        while(true) {
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



