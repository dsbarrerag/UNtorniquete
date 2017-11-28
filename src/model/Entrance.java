package model;

import controller.App;
import generator.Generator;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Entrance implements Observer{

    private ArrayList<User> entryQueue = new ArrayList();
    private ArrayList<User> exitQueue = new ArrayList();
    private Generator entryGenerator, exitGenerator;

    private ArrayList<Turnstile> turnstiles;

    private Random random;

    public Entrance() {
        random = new Random();
    }

    public void setTurnstiles(int num, double delayMean){
        turnstiles = new ArrayList<>();
        initTurnstiles(num, delayMean);
    }

    private void initTurnstiles(int num, double delayMean){
        for(int i=0; i < num; i++){
            Turnstile turnstile = new Turnstile(i, delayMean, true);
            turnstile.addObserver(this);
            turnstiles.add(turnstile);
        }
    }

    public void setEntryGenerator(Generator generator){
        if(entryGenerator != null) {
            entryGenerator.deleteObserver(this);
            entryGenerator.active = false;
        }
        entryGenerator = generator;
        entryGenerator.addObserver(this);
        new Thread(entryGenerator).start();
    }

    public void setExitGenerator(Generator generator){
        if(exitGenerator != null) {
            exitGenerator.deleteObserver(this);
            exitGenerator.active = false;
        }
        exitGenerator = generator;
        exitGenerator.addObserver(this);
        new Thread(exitGenerator).start();
    }

    @Override
    public synchronized void update(Observable observable, Object o) {

        if( observable.equals(exitGenerator) ) {
            User user = new User(String.valueOf(random.nextInt()));
            exitQueue.add(user);
            App.getInstance().createEntryUser(user);
            System.out.println("En cola para salir: " + exitQueue.size());
        } else if( observable.equals(entryGenerator) ){
            User user = new User(String.valueOf(random.nextInt()));
            entryQueue.add(user);
            App.getInstance().createExitUser(user);
            System.out.println("En cola para entrar: " + entryQueue.size());
        }
        if(observable.getClass().equals(Turnstile.class)){
            Turnstile current = (Turnstile) observable;
            App.getInstance().freeTurnstile(current);
            System.out.println("Se desocupa el torniquete: " + current.getId());
        }
        service();

    }

    private synchronized void service(){
        if(entryQueue.size() > 0 )
            getIn();
        if(exitQueue.size() > 0)
            getOut();
    }

    private synchronized void getIn(){
        for(Turnstile t: turnstiles){
            if(t.isEnabled() && t.isEntry() && !t.isBusy()){
                entryQueue.remove(0);
                App.getInstance().userIn(t);
                new Thread(t).start();
                break;
            }
        }
    }

    private synchronized void getOut(){
        for(Turnstile t: turnstiles){
            if(t.isEnabled() && !t.isEntry() && !t.isBusy()){
                exitQueue.remove(0);
                App.getInstance().userOut(t);
                new Thread(t).start();
                break;
            }
        }
    }

    public Generator getEntryGenerator() {
        return entryGenerator;
    }

    public Generator getExitGenerator() {
        return exitGenerator;
    }

    public ArrayList<User> getEntryQueue() {
        return entryQueue;
    }

    public ArrayList<User> getExitQueue() {
        return exitQueue;
    }

    public ArrayList<Turnstile> getTurnstiles() {
        return turnstiles;
    }

    // Este es el metodo machete de la semana
    public void setTurnstilesState(int entryTurnstiles, int exitTurnstiles){
        int enableTurnstiles = entryTurnstiles + exitTurnstiles;
        if(enableTurnstiles <= turnstiles.size()){
            for (int i=0; i < entryTurnstiles; i++){
                turnstiles.get(i).setEntry(true);
                turnstiles.get(i).setEnabled(true);
            }
            for (int i=0; i < exitTurnstiles; i++){
                turnstiles.get(i+entryTurnstiles).setEntry(false);
                turnstiles.get(i+entryTurnstiles).setEnabled(true);
            }
            for (int i = enableTurnstiles; i < turnstiles.size(); i++){
                turnstiles.get(i).setEnabled(false);
            }
        }
        /*for(Turnstile t: turnstiles){
            System.out.println("Torniquete: " + t.getId() + " Es entrada: " + t.isEntry());
        }*/
    }

}
