package model;

import controller.App;
import generator.Generator;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Entrance implements Observer{

    private boolean bn;

    private ArrayList<User> entryQueue = new ArrayList();
    private ArrayList<User> exitQueue = new ArrayList();
    private Generator entryGenerator, exitGenetator;

    private ArrayList<Turnstile> turnstiles;

    private Random random;

    public Entrance(int num, double delayMean) {
        random = new Random();
        //System.out.println("creados " + num + " torniquetes");
        initTurnstiles(num, delayMean);
    }

    private void initTurnstiles(int num, double delayMean){
        turnstiles = new ArrayList<>();
        for(int i=0; i < num; i++){
            Turnstile turnstile = new Turnstile(i, delayMean, true);
            turnstile.addObserver(this);
            turnstiles.add(turnstile);
        }
    }

    public void setEntryGenerator(Generator generator){
        entryGenerator = generator;
        entryGenerator.addObserver(this);
        new Thread(entryGenerator).start();
    }

    public void setExitGenerator(Generator generator){
        exitGenetator = generator;
        exitGenetator.addObserver(this);
        new Thread(exitGenetator).start();
    }

    @Override
    public synchronized void update(Observable observable, Object o) {

        if( observable.equals(exitGenetator) ) {
            exitQueue.add(new User(String.valueOf(random.nextInt())));
            //System.out.println("En cola para salir: " + exitQueue.size());
        } else if( observable.equals(entryGenerator) ){
            entryQueue.add(new User(String.valueOf(random.nextInt())));
            //System.out.println("En cola para entrar: " + entryQueue.size());
        }
        if(observable.getClass().equals(Turnstile.class)){
            Turnstile current = (Turnstile) observable;
            //System.out.println("Se desocupa el torniquete: " + current.getId());
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
                new Thread(t).start();
                break;
            }
        }
    }

    private synchronized void getOut(){
        for(Turnstile t: turnstiles){
            if(t.isEnabled() && !t.isEntry() && !t.isBusy()){
                exitQueue.remove(0);
                new Thread(t).start();
                break;
            }
        }
    }


    public void setTurnstilesState(int entryTurnstiles, int exitTurnstiles){
        int enableTurnstiles = entryTurnstiles + exitTurnstiles;
        if(enableTurnstiles <= turnstiles.size()){
            for (int i=0; i < entryTurnstiles; i++){
                turnstiles.get(i).setEntry(true);
                turnstiles.get(enableTurnstiles).setEnabled(true);
            }
            for (int i=0; i < exitTurnstiles; i++){
                turnstiles.get(i+entryTurnstiles).setEntry(false);
                turnstiles.get(enableTurnstiles).setEnabled(true);
            }
            for (int i = enableTurnstiles; i < turnstiles.size(); i++){
                turnstiles.get(i).setEnabled(false);
            }
        }
    }

}
