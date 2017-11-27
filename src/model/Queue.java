package model;

import analysis.Calculator.QueueCalculator;

public class Queue {
    private double lambda, mu, lq, r, rho, wq;
    private int servers;

    private QueueCalculator calculator;

    public Queue(double lambda, double mu, int servers) {
        this.lambda = lambda;
        this.mu = mu;
        this.servers = servers;
        this.lq = Double.MAX_VALUE;
        this.wq = Double.MAX_VALUE;
        this.r = Double.MAX_VALUE;
        this.rho = Double.MAX_VALUE;
    }

    public Queue(double lambda, double mu){
        this.lambda = lambda;
        this.mu = mu;
        this.servers = 1;
        this.lq = Double.MAX_VALUE;
        this.wq = Double.MAX_VALUE;
        this.r = Double.MAX_VALUE;
        this.rho = Double.MAX_VALUE;
    }

    public void setCalculator(QueueCalculator calculator) {
        this.calculator = calculator;
        try {
            calculator.setParams(mu, lambda, servers);
            lq = calculator.getLq();
            wq = calculator.getWq();
            r = calculator.getR();
            rho = calculator.getRho();
        }catch (Exception e){
            this.lq = Double.MAX_VALUE;
            this.wq = Double.MAX_VALUE;
            this.r = Double.MAX_VALUE;
            this.rho = Double.MAX_VALUE;
        }
    }

    public double getLambda() {
        return lambda;
    }

    public double getMu() {
        return mu;
    }

    public double getLq() {
        return lq;
    }

    public double getR() {
        return r;
    }

    public double getRho() {
        return rho;
    }

    public double getWq() {
        return wq;
    }

    public int getServers() {
        return servers;
    }

    public QueueCalculator getCalculator() {
        return calculator;
    }

    public void setServers(int servers) {
        this.servers = servers;
    }

    @Override
    public String toString() {
        return "Queue{" +
                "lambda=" + lambda +
                ", mu=" + mu +
                ", lq=" + lq +
                ", r=" + r +
                ", rho=" + rho +
                ", wq=" + wq +
                ", servers=" + servers +
                '}';
    }
}
