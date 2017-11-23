package model;

public class Queue {
    public double lambda, mu, lq, r, rho, wq;
    public int servers;

    public Queue(double lambda, double mu, int servers) {
        this.lambda = lambda;
        this.mu = mu;
        this.servers = servers;
    }
}
