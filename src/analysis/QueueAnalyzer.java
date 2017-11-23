package analysis;

import analysis.Calculator.MMCCalculator;
import analysis.Calculator.QueueCalculator;
import controller.App;
import model.Queue;

public class QueueAnalyzer {

    private final double optimalRho = 0.5;

    private int entryTurnstiles, exitTurnstiles, totalTurnstiles;

    private Queue entryQueue, exitQueue;

    public QueueAnalyzer(){
    }

    public void setTurnstiles(int turnstiles) {
        initializeTurnstiles(turnstiles);
        totalTurnstiles = turnstiles;
    }

    public void calculateBest(){
        entryQueue.servers = entryTurnstiles;
        exitQueue.servers = exitTurnstiles;

        entryTurnstiles = reduceTurnstiles(entryTurnstiles, new MMCCalculator(entryQueue), 0);
        exitTurnstiles = reduceTurnstiles(exitTurnstiles, new MMCCalculator(exitQueue), 0);

        moveTurnstiles(entryQueue, exitQueue, 0);

        App.getInstance().setTurnstilesState(entryTurnstiles, exitTurnstiles);
    }


    private double moveTurnstiles(Queue iQueue, Queue oQueue, int depth){

        double diference = 0.0;
        double addExit = 0.0;
        double addEntry = 0.0;

        QueueCalculator iCalculator = new MMCCalculator(iQueue);
        QueueCalculator oCalculator = new MMCCalculator(oQueue);

        try {
            iCalculator.calculate();
            oCalculator.calculate();
        }catch (ArithmeticException ex){
            System.out.println(ex);
            return 0.0;
        }
        double currentDif = Math.abs(iCalculator.getQueue().wq - oCalculator.getQueue().wq);

        if(depth <= 3) {
            if (iQueue.servers > 1)
                addExit = moveTurnstiles(new Queue(iQueue.lambda, iQueue.mu, iQueue.servers - 1),
                        new Queue(oQueue.lambda, oQueue.mu, oQueue.servers + 1), depth + 1);

            if (oQueue.servers > 1)
                addEntry = moveTurnstiles(new Queue(iQueue.lambda, iQueue.mu, iQueue.servers + 1),
                        new Queue(oQueue.lambda, oQueue.mu, oQueue.servers - 1), depth + 1);

            if(addEntry == 0.0 && addExit == 0.0) {
                diference = 0.0;
            }
            else if(addEntry == 0.0) {
                diference = Math.min(addExit,currentDif);
            }
            else if(addExit == 0.0) {
                diference = Math.min(addEntry,currentDif);
            }
            else {
                diference = Math.min(Math.min(addEntry, addExit), currentDif);
            }

        }else
            diference = currentDif;

        setFields(currentDif, addEntry, addExit, diference, iQueue, oQueue);

        return diference;
    }


    private void setFields(double currentDif, double addEntry, double addExit, double diference, Queue iQueue, Queue oQueue){
        if(diference == currentDif){
            entryTurnstiles = iQueue.servers;
            exitTurnstiles = oQueue.servers;
        }else if(diference == addEntry){
            entryTurnstiles = iQueue.servers+1;
            exitTurnstiles = oQueue.servers-1;
        }else if(diference == addExit){
            entryTurnstiles = iQueue.servers-1;
            exitTurnstiles = oQueue.servers+1;
        }

    }

    private int reduceTurnstiles(int turnstiles, QueueCalculator calculator, int depth){
        try {
            calculator.calculate();
            if(calculator.getQueue().rho < optimalRho && calculator.getQueue().servers > 1){
                calculator.getQueue().servers -= 1;
                reduceTurnstiles(turnstiles, calculator, depth++);
            }
        }catch (ArithmeticException e){
            if(depth > 0)
                calculator.getQueue().servers += 1;
        }
        return calculator.getQueue().servers >= turnstiles ? turnstiles : (calculator.getQueue().servers + 1);
    }

    private void initializeTurnstiles(int numTurnstiles) {
        if(numTurnstiles % 2 == 0){
            entryTurnstiles = numTurnstiles / 2;
            exitTurnstiles = numTurnstiles / 2;
        } else{
            int aux = numTurnstiles - 1;
            entryTurnstiles = (aux/2)+1;
            exitTurnstiles = aux/2;
        }
    }

    public Queue getEntryQueue() {
        return entryQueue;
    }

    public void setEntryQueue(Queue entryQueue) {
        this.entryQueue = entryQueue;
    }

    public Queue getExitQueue() {
        return exitQueue;
    }

    public void setExitQueue(Queue exitQueue) {
        this.exitQueue = exitQueue;
    }

    public int getEntryTurnstiles() {
        return entryTurnstiles;
    }

    public int getExitTurnstiles() {
        return exitTurnstiles;
    }
}
