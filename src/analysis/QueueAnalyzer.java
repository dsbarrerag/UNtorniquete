package analysis;

import analysis.Calculator.MMCCalculator;
import analysis.Calculator.QueueCalculator;
import controller.App;
import model.Queue;

import java.util.HashMap;

public class QueueAnalyzer {

    private final double optimalRho = 0.5;
    private int max_depth = 1;

    private int entryTurnstiles, exitTurnstiles;
    private Queue entryQueue, exitQueue;
    private HashMap<Double,Integer[]> bestValue;

    public QueueAnalyzer(){
    }

    public void setTurnstiles(int turnstiles) {
        initializeTurnstiles(turnstiles);

    }

    public void calculateBest(){
        if(entryQueue != null && exitQueue != null) {
            bestValue = new HashMap<>();

            entryQueue.setServers(entryTurnstiles);
            exitQueue.setServers(exitTurnstiles);

            double bestDif = moveTurnstiles(entryQueue, exitQueue, 0);

            if(bestDif == Double.MAX_VALUE)
                App.getInstance().badQueue();
            else{
                Integer[] best = bestValue.get(bestDif);

                entryTurnstiles = best[0];
                exitTurnstiles = best[1];

            }
            System.out.println("ENTRADA: " + entryTurnstiles);
            System.out.println("SALIDA: " + exitTurnstiles);

            App.getInstance().setTurnstilesState(entryTurnstiles, exitTurnstiles);
        }
    }


    private double moveTurnstiles(Queue iQueue, Queue oQueue, int depth){

        iQueue.setCalculator(new MMCCalculator());
        oQueue.setCalculator(new MMCCalculator());

        double entryDif = Double.MAX_VALUE;
        double exitDif = Double.MAX_VALUE;
        double currentDif = getDif(iQueue.getWq(), oQueue.getWq());

        if(depth <= max_depth){
            if (oQueue.getServers() > 1)
                entryDif = moveTurnstiles(new Queue(iQueue.getLambda(), iQueue.getMu(), iQueue.getServers() + 1),
                        new Queue(oQueue.getLambda(), oQueue.getMu(), oQueue.getServers() - 1), depth + 1);

            if (iQueue.getServers() > 1)
                exitDif = moveTurnstiles(new Queue(iQueue.getLambda(), iQueue.getMu(), iQueue.getServers() - 1),
                        new Queue(oQueue.getLambda(), oQueue.getMu(), oQueue.getServers() + 1), depth + 1);
        }

        double min = Double.min(Double.min(entryDif, exitDif), currentDif);

        if(min == currentDif)
            bestValue.put(min, new Integer[]{iQueue.getServers(), oQueue.getServers()});

        return min;
    }

    private double getDif(double a, double b){
        double dif;
        if(a == Double.MAX_VALUE || b == Double.MAX_VALUE)
            dif = Double.MAX_VALUE;
        else
            dif = Math.abs(a-b);
        return dif;
    }


    private int reduceTurnstiles(int turnstiles, QueueCalculator calculator, int depth){
      return 0;
    }

    private void initializeTurnstiles(int numTurnstiles) {
        if(numTurnstiles % 2 == 0){
            entryTurnstiles = numTurnstiles / 2;
            exitTurnstiles = numTurnstiles / 2;
            max_depth = numTurnstiles / 2;
        } else{
            int aux = numTurnstiles - 1;
            entryTurnstiles = (aux/2)+1;
            exitTurnstiles = aux/2;
            max_depth = aux/2;
        }
    }

    public Queue getEntryQueue() {
        return entryQueue;
    }

    public void setEntryQueue(Queue entryQueue) {
        this.entryQueue = entryQueue;
        calculateBest();
    }

    public Queue getExitQueue() {
        return exitQueue;
    }

    public void setExitQueue(Queue exitQueue) {
        this.exitQueue = exitQueue;
        calculateBest();
    }

    public int getEntryTurnstiles() {
        return entryTurnstiles;
    }

    public int getExitTurnstiles() {
        return exitTurnstiles;
    }
}
