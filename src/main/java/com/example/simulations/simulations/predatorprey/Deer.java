package com.example.simulations.simulations.predatorprey;

import javafx.scene.paint.Color;

import java.util.List;

public class Deer extends Animal {

    private boolean killed = false;

    Deer(double x, double y) {
        super(300, 60, 25, x, y, Color.SANDYBROWN);

        // 0.4 <= velocityX < 0.7 or -0.4 <= velocityX < -0.7
        velocityX = Math.random() * (0.9 - 0.6) + 0.6;
        if (Math.random() > 0.5)
            invertVelocityX();

        // 0.4 <= velocityY < 0.7 or -0.4 <= velocityY < -0.7
        velocityY = Math.random() * (0.9 - 0.6) + 0.6;
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

            if (d != this && xDistance < getRadius()/3 && yDistance < getRadius()/3 && !d.isDead()) {
                return;
            }
        }


        if (Math.random() < 0.8) {
            newbornDeer.add(new Deer(getX(), getY()));
        }
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
