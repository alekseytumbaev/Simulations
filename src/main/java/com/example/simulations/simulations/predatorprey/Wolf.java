package com.example.simulations.simulations.predatorprey;

import javafx.scene.paint.Color;

import java.util.List;

public class Wolf extends Animal{

    private int satiety = PPConfig.W_SATIETY();

    protected Wolf(double x, double y) {
        super(PPConfig.W_LIFETIME(), PPConfig.W_BREEDING_PERIOD(), PPConfig.W_RADIUS(), x, y, Color.GRAY);

        velocityX = PPConfig.W_VELOCITY();
        if (Math.random() > 0.5)
            invertVelocityX();

        velocityY = PPConfig.W_VELOCITY();
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

        if (Math.random() < (double) (satiety - getBreedingPeriod())/ PPConfig.W_SATIETY())
            for (int i = 0; i < PPConfig.W_CUBS_PER_BIRTH(); i++)
                newbornWolves.add(new Wolf(getX(),getY()));
    }

    void hunt(Deer d) {
        if (satiety > PPConfig.W_SATIETY())
            return;
        if (d.isDead())
            return;

        double xDistance = Math.abs(getX() - d.getX());
        double yDistance = Math.abs(getY() - d.getY());

        //eat deer if it's close enough
        if (xDistance < getRadius() && yDistance < getRadius()) {
            satiety += d.calories;
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
