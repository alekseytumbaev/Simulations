package com.example.simulations.simulations;

public abstract class AbstractSimulation implements Simulation {

    private final double width;
    private final double height;

    protected AbstractSimulation(double width, double height) {
        if (width < 1 || height < 1)
            throw new IllegalArgumentException("Width or height was less than one.");
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }
}
