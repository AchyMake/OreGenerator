package org.achymake.oregenerator.handlers;

import java.util.Random;

public class RandomHandler {
    private Random getRandom() {
        return new Random();
    }
    private double getRandomDouble() {
        return getRandom().nextDouble(0, 1);
    }
    public boolean isTrue(double chance) {
        return chance >= getRandomDouble();
    }
}