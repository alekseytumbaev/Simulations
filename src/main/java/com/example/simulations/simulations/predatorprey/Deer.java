package com.example.simulations.simulations.predatorprey;

import javafx.scene.paint.Color;

import java.util.List;

public class Deer extends Animal {

    public final int calories = PPConfig.D_CALORIES();
    private boolean killed = false;

    Deer(double x, double y) {
        super(PPConfig.D_LIFETIME(), PPConfig.D_BREEDING_PERIOD(), PPConfig.D_RADIUS(), x, y, Color.SANDYBROWN);

        velocityX = PPConfig.D_VELOCITY();
        if (Math.random() > 0.5)
            invertVelocityX();

        velocityY = PPConfig.D_VELOCITY();
        if (Math.random() > 0.5)
            invertVelocityY();
    }

    void move(List<Deer> deer, List<Deer> newbornDeer) {
        if (isDead())
            return;

        giveBirth(deer,newbornDeer);

        moveAlongX();
        moveAlongY();

        incrementAge();
    }

    private void giveBirth(List<Deer> deer, List<Deer> newbornDeer) {
        if (isDead() || getAge() == 0 || getAge() % getBreedingPeriod() != 0)
            return;

        for (Deer d : deer) {
            double xDistance = Math.abs(getX() - d.getX());
            double yDistance = Math.abs(getY() - d.getY());

            if (d != this && xDistance < getRadius()/3 && yDistance < getRadius()/3 && !d.isDead())
                return;
        }

        if (Math.random() < 0.8)
            for (int i = 0; i < PPConfig.D_CUBS_PER_BIRTH(); i++)
                newbornDeer.add(new Deer(getX(), getY()));
    }

    void kill() {
        killed = true;
    }

    @Override
    protected boolean isDead() {
        if (killed)
            return true;

        return getAge() >= getLifetime();
    }
}
