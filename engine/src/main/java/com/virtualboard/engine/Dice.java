package com.virtualboard.engine;

import java.util.Random;

public class Dice {
    private int sides;
    private Random random;

    public Dice() {
        this(6);
    }

    public Dice(int sides) {
        this.sides = sides;
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(sides) + 1;
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        if(sides > 0) {
            this.sides = sides;
        }
    }
}
