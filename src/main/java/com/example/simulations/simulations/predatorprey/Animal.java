package com.example.simulations.simulations.predatorprey;

import com.example.simulations.simulations.AbstractBall;
import javafx.scene.paint.Color;

abstract class Animal extends AbstractBall {

    private final int breedingPeriod;
    private final int lifetime;
    private int age = 0;

    protected Animal(int lifetime, int breedingPeriod, double radius, double x, double y, Color color) {
        super(radius, x, y, color);

        if (lifetime < 1 || breedingPeriod < 1)
            throw new IllegalArgumentException("Lifetime or breeding age was less than zero.");

        if (lifetime <= breedingPeriod)
            throw new IllegalArgumentException("Lifetime was less or equal to breeding age.");

        this.breedingPeriod = breedingPeriod;
        this.lifetime = lifetime;
    }
    protected void invertVelocityX() {
        velocityX *= -1;
    }

    protected void invertVelocityY() {
        velocityY *= -1;
    }

    protected void incrementAge() {
        age++;
    }

    protected abstract boolean isDead();

    protected int getBreedingPeriod() {
        return breedingPeriod;
    }

    public int getAge() {
        return age;
    }

    public int getLifetime() {
        return lifetime;
    }
}
