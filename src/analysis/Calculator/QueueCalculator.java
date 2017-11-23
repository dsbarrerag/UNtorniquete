package analysis.Calculator;

import model.Queue;

public abstract class QueueCalculator {

    protected Queue queue;


    public abstract void calculate();


    public Queue getQueue(){
        return queue;
    }
}
