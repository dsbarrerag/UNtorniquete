package analysis.Calculator;

import model.Queue;

import static org.apache.commons.math3.util.ArithmeticUtils.factorialDouble;

public class MMCCalculator extends QueueCalculator {

    public MMCCalculator(Queue queue) {
        this.queue = queue;
    }

    private double getP0(){
        double P0 = 0;
        for(int i = 0; i < queue.servers; i++)
            P0 += (Math.pow(queue.r,i)/ factorialDouble(i));
        P0 += (Math.pow(queue.r, queue.servers)/(factorialDouble(queue.servers)*(1-queue.rho)));
        P0 = Math.pow(P0, -1);
        return P0;
    }

    @Override
    public void calculate() {
        boolean optimal = (queue.lambda < queue.mu * queue.servers);
        if(optimal && queue.mu > 0) {
            queue.r = queue.lambda / queue.mu;
            queue.rho = queue.lambda / (queue.servers*queue.mu);
            queue.lq = (((Math.pow(queue.r,queue.servers)*queue.rho)/(factorialDouble(queue.servers)*Math.pow((1-queue.rho),2)))*getP0());
            queue.wq = queue.lq/queue.mu;
        } else{
            throw new ArithmeticException("No se cumple el criterio de estabilidad");
        }

    }
}
