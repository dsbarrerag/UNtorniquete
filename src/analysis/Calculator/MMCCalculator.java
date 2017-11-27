package analysis.Calculator;

import static org.apache.commons.math3.util.ArithmeticUtils.factorialDouble;

public class MMCCalculator extends QueueCalculator {


    public MMCCalculator() {
    }

    private double getP0(){
        double P0 = 0;
        for(int i = 0; i < servers; i++)
            P0 += (Math.pow(getR(),i)/ factorialDouble(i));
        P0 += (Math.pow(getR(), servers)/(factorialDouble(servers)*(1-getRho())));
        P0 = Math.pow(P0, -1);
        return P0;
    }

    @Override
    public double getWq() {
        return getLq()/mu;
    }

    @Override
    public double getLq() {
        return (((Math.pow(getR(),servers)*getRho())/(factorialDouble(servers)*Math.pow((1-getRho()),2)))*getP0());
    }

    @Override
    public double getR() {
        return lambda /mu;
    }

    @Override
    public double getRho() {
        return lambda /(mu*servers);
    }

    @Override
    protected boolean isOptimal() {
        return (lambda < mu * servers && mu > 0);
    }
}
