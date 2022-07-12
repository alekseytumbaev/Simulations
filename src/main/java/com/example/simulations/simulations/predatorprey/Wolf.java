package com.example.simulations.simulations.predatorprey;

import javafx.scene.paint.Color;

import java.util.List;

public class Wolf extends Animal{

    private static final int INITIAL_SATIETY = 400;
    private int satiety = INITIAL_SATIETY;

    protected Wolf(double x, double y) {
        super(700, 70, 25, x, y, Color.GRAY);

        // 0.4 <= velocityX < 0.7 or -0.4 <= velocityX < -0.7
        velocityX = Math.random() * (0.7 - 0.4) + 0.4;
        if (Math.random() > 0.5)
            invertVelocityX();

        // 0.4 <= velocityY < 0.7 or -0.4 <= velocityY < -0.7
        velocityY = Math.random() * (0.7 - 0.4) + 0.4;
        if (Math.random() > 0.5)
            invertVelocityY();
    }

    void move(List<Wolf> wolves) {
        if (isDead())
            return;

        giveBirth(wolves);

        moveAlongX();
        moveAlongY();

        incrementAge();
        satiety--;
    }

    private void giveBirth(List<Wolf> newbornWolves) {
        if (isDead() || getAge() == 0 || getAge() % getBreedingPeriod() != 0)
            return;

        if (satiety > INITIAL_SATIETY - getBreedingPeriod())
            newbornWolves.add(new Wolf(getX(),getY()));
    }

    void hunt(Deer d) {
        if (d.isDead())
            return;

        double xDistance = Math.abs(getX() - d.getX());
        double yDistance = Math.abs(getY() - d.getY());

        //eat deer if it's close enough
        if (xDistance < getRadius() && yDistance < getRadius()) {
            satiety += 13;
            d.kill();
        }
    }

    @Override
    protected boolean isDead() {
        if (satiety == 0)
            return true;

        return getAge() >= getLifetime();
    }
}
