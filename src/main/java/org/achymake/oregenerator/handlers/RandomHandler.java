package org.achymake.oregenerator.handlers;

import java.util.Random;

public class RandomHandler {
    private Random getRandom() {
        return new Random();
    }
    public double nextDouble(double origin, double bound) {
        return getRandom().nextDouble(origin, bound);
    }
    private double getRandomDouble() {
        return nextDouble(0.0, 1.0);
    }
    public boolean isTrue(double chance) {
        return chance >= getRandomDouble();
    }
}