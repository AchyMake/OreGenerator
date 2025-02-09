package org.achymake.oregenerator.handlers;

import java.util.Random;

public class RandomHandler {
    private Random getRandom() {
        return new Random();
    }
    private double getRandomDouble() {
        return 1.0 - getRandom().nextDouble(1.0);
    }
    public boolean isTrue(double chance) {
        return chance >= getRandomDouble();
    }
}