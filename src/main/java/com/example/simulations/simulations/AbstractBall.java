package com.example.simulations.simulations;

import javafx.scene.paint.Color;

public abstract class AbstractBall implements Ball{

    private final double radius;
    private double x;
    private double y;
    protected double velocityX;
    protected double velocityY;
    private Color color;

    protected AbstractBall(double radius, double x, double y, Color color) {
        if (radius < 1)
            throw new IllegalArgumentException("Radius was less than one.");
        if (x < 0 || y < 0)
            throw new IllegalArgumentException("X or Y was less than zero.");

        this.radius = radius;
        this.x = x;
        this.y = y;
        setColor(color);
    }

    protected void moveAlongX() {
        x += velocityX;
        if (x < 0)
            x = 0;
    }

    protected void moveAlongY() {
        y += velocityY;
        if (y < 0)
            y = 0;
    }

    protected void setColor(Color color) {
        this.color = color;
    }


    //getters
    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }
}
