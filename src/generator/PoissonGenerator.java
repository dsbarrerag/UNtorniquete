package generator;

import org.apache.commons.math3.distribution.*;

public class PoissonGenerator extends Generator {

    private ExponentialDistribution generator;

    public PoissonGenerator(double mean) {
        generator = new ExponentialDistribution(mean);
    }

    @Override
    long nextTime() {
        return (long) (generator.sample()*1000);
    }
}
