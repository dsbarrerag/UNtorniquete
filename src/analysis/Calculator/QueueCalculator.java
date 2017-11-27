package analysis.Calculator;

import model.Queue;

public abstract class QueueCalculator {

    protected double mu, lambda;
    protected int servers;

    public void setParams(double mu, double lambda, int servers){
        this.lambda = lambda;
        this.mu = mu;
        this.servers = servers;
        if(!isOptimal()){
            throw  new ArithmeticException("No se cumple la condicion de estabilidad");
        }
    }

    public abstract double getWq();
    public abstract double getLq();
    public abstract double getR();
    public abstract double getRho();

    protected abstract boolean isOptimal();

}
