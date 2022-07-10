package com.example.simulations.simulations;

import javafx.scene.paint.Color;

public abstract class Ball {

    protected final double radius;
    protected double x;
    protected double y;
    protected double velocityX;
    protected double velocityY;

    protected Color color;

    protected Ball(int radius) {
        this.radius = radius;
    }

    //getters
    public double getRadius() {
        return radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public Color getColor() {
        return color;
    }
}
